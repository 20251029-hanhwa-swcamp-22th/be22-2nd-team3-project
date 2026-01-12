package com.ohgiraffers.hw22thteamproject.recipe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeCreateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeUpdateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.service.RecipeCommandService;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishCategoryDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDetailResponse;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.service.RecipeQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RecipeControllersIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RecipeCommandService recipeCommandService;

    @MockitoBean
    private RecipeQueryService recipeQueryService;

    @Test
    @DisplayName("레시피 등록 테스트")
    @WithMockUser(username = "gusgh075", password = "hidden")
    void registRecipeTest() throws Exception {
        // given
        RecipeCreateRequest request = new RecipeCreateRequest();
        request.setDishName("Test Dish");
        request.setDishImgFileRoute("/images/test.jpg");
        request.setDishCategoryNo(1);
        request.setUserId("gusgh075");

        RecipeCreateRequest.RecipeStepRequest step = new RecipeCreateRequest.RecipeStepRequest();
        step.setRecipeIngredient("Test Ingredient");
        step.setRecipeCookery("Test Cookery");
        request.setRecipes(List.of(step));

        given(recipeCommandService.registRecipe(any(RecipeCreateRequest.class), eq("gusgh075")))
                .willReturn(1);

        // when & then
        mockMvc.perform(post("/api/v1/recipes")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").value(1));
    }

    @Test
    @DisplayName("레시피 수정 테스트")
    @WithMockUser(username = "gusgh075", password = "hidden")
    void updateRecipeTest() throws Exception {
        // given
        int dishNo = 1;
        RecipeUpdateRequest request = new RecipeUpdateRequest();
        request.setDishName("Updated Dish");
        request.setDishImgFileRoute("/images/updated.jpg");
        request.setDishCategoryNo(2);

        RecipeUpdateRequest.RecipeStepRequest step = new RecipeUpdateRequest.RecipeStepRequest();
        step.setRecipeIngredient("Updated Ingredient");
        step.setRecipeCookery("Updated Cookery");
        request.setRecipes(List.of(step));

        // when & then
        mockMvc.perform(put("/api/v1/recipes/{dishNo}", dishNo)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("레시피 삭제 테스트")
    @WithMockUser(username = "gusgh075", password = "hidden")
    void deleteRecipeTest() throws Exception {
        // given
        int dishNo = 1;

        // when & then
        mockMvc.perform(delete("/api/v1/recipes/{dishNo}", dishNo)
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("전체 요리 카테고리 목록 조회 테스트")
    @WithMockUser(username = "gusgh075", password = "hidden")
    void getAllCategoriesTest() throws Exception {
        // given
        DishCategoryDTO category = new DishCategoryDTO();
        category.setDishCategoryNo(1);
        category.setDishCategoryName("Korean");

        given(recipeQueryService.findAllCategories()).willReturn(List.of(category));

        // when & then
        mockMvc.perform(get("/api/v1/recipes/categories")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].dishCategoryName").value("Korean"));
    }

    @Test
    @DisplayName("내 레시피 조회 테스트")
    @WithMockUser(username = "gusgh075", password = "hidden")
    void getMyDishesTest() throws Exception {
        // given
        DishDTO dish = new DishDTO();
        dish.setDishNo(1);
        dish.setDishName("My Dish");

        given(recipeQueryService.findDishesByUsername("gusgh075")).willReturn(List.of(dish));

        // when & then
        mockMvc.perform(get("/api/v1/recipes/my")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].dishName").value("My Dish"));
    }

    @Test
    @DisplayName("특정 사용자 레시피 조회 테스트")
    @WithMockUser(username = "gusgh075", password = "hidden")
    void getDishesByUserTest() throws Exception {
        // given
        int userNo = 1;
        DishDTO dish = new DishDTO();
        dish.setDishNo(1);
        dish.setDishName("User Dish");

        given(recipeQueryService.findDishesByUserNo(userNo)).willReturn(List.of(dish));

        // when & then
        mockMvc.perform(get("/api/v1/recipes/users/{userNo}", userNo)
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].dishName").value("User Dish"));
    }

    @Test
    @DisplayName("레시피 상세 조회 테스트")
    @WithMockUser(username = "gusgh075", password = "hidden")
    void getRecipeDetailTest() throws Exception {
        // given
        int dishNo = 1;

        DishDTO dish = new DishDTO();
        dish.setDishNo(dishNo);
        dish.setDishName("Detail Dish");

        RecipeDTO recipe = new RecipeDTO();
        recipe.setRecipeNo(1);
        recipe.setRecipeCookery("Detail Cookery");

        RecipeDetailResponse response = new RecipeDetailResponse(dish, List.of(recipe));

        given(recipeQueryService.getRecipeDetail(dishNo)).willReturn(response);

        // when & then
        mockMvc.perform(get("/api/v1/recipes/{dishNo}", dishNo)
                .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.dish.dishName").value("Detail Dish"))
                .andExpect(jsonPath("$.data.recipes[0].recipeCookery").value("Detail Cookery"));
    }
}
