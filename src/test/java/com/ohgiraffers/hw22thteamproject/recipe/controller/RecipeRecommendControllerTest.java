package com.ohgiraffers.hw22thteamproject.recipe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecipeRecommendResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.service.RecipeRecommendService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.ohgiraffers.hw22thteamproject.user.query.dto.response.UserDTO;
import com.ohgiraffers.hw22thteamproject.user.query.mapper.UserMapper;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeRecommendControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockitoBean
  private UserMapper userMapper;

  @MockitoBean
  private RecipeRecommendService recipeRecommendService;

  @Test
  @DisplayName("레시피 추천 API 호출 시 성공적으로 응답하고 DB에 저장되어야 한다")
  @WithMockUser(username = "testUser")
  void recommendRecipeTest() throws Exception {
    // 1. 가짜 요청(Request) 데이터 준비
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

    // 2. 가짜 응답(Response) 및 내부 서비스 동작 설정
    RecipeRecommendResponse mockResponse = RecipeRecommendResponse.builder()
        .dishCategory(RecipeRecommendResponse.DishCategory.KOREAN)
        .dishName("참치 계란 덮밥")
        .ingredients(List.of(
            new RecipeRecommendResponse.IngredientDetail("참치캔", 1.0, "캔"),
            new RecipeRecommendResponse.IngredientDetail("계란", 2.0, "개")
        ))
        .substitutions(List.of(
            new RecipeRecommendResponse.Substitution("양파", "대파")
        ))
        .cookery(List.of("1. 양파를 볶는다", "2. 참치를 넣는다"))
        .tips(List.of("마요네즈를 추가하면 맛있어요"))
        .build();

    // 서비스 계층의 의존성 모킹
    given(recipeRecommendService.getRecipeRecommendation(any(RecipeRecommendRequest.class)))
        .willReturn(mockResponse);

    UserDTO mockUserDTO = UserDTO.builder().userNo(1L).build();
    given(userMapper.selectUserByUserId("testUser")).willReturn(mockUserDTO);

    // 3. API 호출 및 검증
    mockMvc.perform(post("/api/v1/recipes/recommend")
            .with(SecurityMockMvcRequestPostProcessors.csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.dishName").value("참치 계란 덮밥"))
        .andExpect(jsonPath("$.dishCategory").value("KOREAN"))
        .andExpect(jsonPath("$.ingredients[0].name").value("참치캔"))
        .andExpect(jsonPath("$.cookery[0]").value("1. 양파를 볶는다"))
        .andExpect(jsonPath("$.tips[0]").value("마요네즈를 추가하면 맛있어요"));
  }
}