package com.ohgiraffers.hw22thteamproject.recipe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecipeRecommendResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.service.RecipeRecommendService;
import com.ohgiraffers.hw22thteamproject.user.query.dto.response.UserDTO;
import com.ohgiraffers.hw22thteamproject.user.query.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecipeRecommendControllerTest {

        @Nested
        @DisplayName("Mock API 테스트")
        @SpringBootTest
        @AutoConfigureMockMvc
        class MockTests {

                @Autowired
                private MockMvc mockMvc;

                @Autowired
                private ObjectMapper objectMapper;

                @MockitoBean
                private UserMapper userMapper;

                @MockitoBean
                private RecipeRecommendService recipeRecommendService;

                @Test
                @DisplayName("Gemini API Mocking: 레시피 추천 성공")
                @WithMockUser(username = "testUser")
                void recommendRecipeMockTest() throws Exception {
                        // given
                        RecipeRecommendRequest request = RecipeRecommendRequest.builder()
                                        .dishName("참치캔 요리")
                                        .ingredients(List.of("참치캔", "양파", "계란"))
                                        .dislikedIngredients(List.of())
                                        .skillLevel(RecipeRecommendRequest.CookingLevel.BEGINNER)
                                        .tools(List.of("프라이팬"))
                                        .foodTypes(List.of("KOREAN"))
                                        .preference("달달한거 좋아해용.")
                                        .servings(1)
                                        .build();

                        RecipeRecommendResponse mockResponse = RecipeRecommendResponse.builder()
                                        .dishCategory(RecipeRecommendResponse.DishCategory.KOREAN)
                                        .dishName("참치 계란 덮밥")
                                        .ingredients(List.of(
                                                        new RecipeRecommendResponse.IngredientDetail("참치캔", 1.0, "캔"),
                                                        new RecipeRecommendResponse.IngredientDetail("계란", 2.0, "개")))
                                        .substitutions(List.of(
                                                        new RecipeRecommendResponse.Substitution("양파", "대파")))
                                        .cookery(List.of("1. 양파를 볶는다", "2. 참치를 넣는다"))
                                        .tips(List.of("마요네즈를 추가하면 맛있어요"))
                                        .build();

                        given(recipeRecommendService.getRecipeRecommendation(any(RecipeRecommendRequest.class)))
                                        .willReturn(mockResponse);

                        UserDTO mockUserDTO = UserDTO.builder().userNo(1L).build();
                        given(userMapper.selectUserByUserId("testUser")).willReturn(mockUserDTO);

                        // when & then
                        mockMvc.perform(post("/api/v1/recipes/recommend")
                                        .with(csrf())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andDo(print())
                                        .andExpect(status().isOk())
                                        .andExpect(jsonPath("$.data.dishName").value("참치 계란 덮밥"))
                                        .andExpect(jsonPath("$.data.dishCategory").value("KOREAN"));
                }
        }

        @Nested
        @DisplayName("Real API 테스트")
        @Tag("external")
        @SpringBootTest
        @AutoConfigureMockMvc
        class RealApiTests {

                @Autowired
                private MockMvc mockMvc;

                @Autowired
                private ObjectMapper objectMapper;

                // 실제 동작을 위해 Mocking 하지 않음.
                // 단, UserMapper 등은 DB 의존성이 있으므로 필요 시 Mocking 하거나 실제 DB 사용.
                // 여기서는 편의상 UserMapper만 Mocking하여 인증 통과 보장 (API 호출 자체는 Real Service 타도록 함)

                @MockitoBean
                private UserMapper userMapper;

                @Test
                @DisplayName("Gemini API Real Call: 실제 응답 확인")
                @WithMockUser(username = "testUser")
                void recommendRecipeRealTest() throws Exception {
                        // given
                        RecipeRecommendRequest request = RecipeRecommendRequest.builder()
                                        .dishName("김치볶음밥")
                                        .ingredients(List.of("김치", "밥", "대파"))
                                        .dislikedIngredients(List.of("당근"))
                                        .skillLevel(RecipeRecommendRequest.CookingLevel.BEGINNER)
                                        .tools(List.of("프라이팬"))
                                        .foodTypes(List.of("한식"))
                                        .preference("맵게 해주세요")
                                        .servings(1)
                                        .build();

                        UserDTO mockUserDTO = UserDTO.builder().userNo(1L).build();
                        given(userMapper.selectUserByUserId("testUser")).willReturn(mockUserDTO);

                        // when & then
                        var result = mockMvc.perform(post("/api/v1/recipes/recommend")
                                        .with(csrf())
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andDo(print())
                                        .andExpect(status().isOk())
                                        .andExpect(jsonPath("$.data.dishName").exists()) // 실제 응답값은 예측 불가하므로 존재 여부만 확인
                                        .andExpect(jsonPath("$.data.cookery").isArray())
                                        .andReturn();

                        String content = result.getResponse()
                                        .getContentAsString(java.nio.charset.StandardCharsets.UTF_8);
                        System.out.println("=================================================");
                        System.out.println("Real Gemini API Response:");
                        System.out.println(content);
                        System.out.println("=================================================");
                }
        }
}