package com.ohgiraffers.hw22thteamproject.recipe.query.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishCategoryDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDetailResponse;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecommendRecipeDTO; // Added
import com.ohgiraffers.hw22thteamproject.recipe.query.service.RecipeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 레시피 조회 전용 REST API 컨트롤러
 */
@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeQueryController {

    private final RecipeQueryService recipeQueryService;

    /**
     * 전체 요리 카테고리 목록을 조회합니다.
     * GET /api/v1/recipes/categories
     */
    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<DishCategoryDTO>>> getAllCategories() {
        List<DishCategoryDTO> categories = recipeQueryService.findAllCategories();
        return ResponseEntity.ok(ApiResponse.success(categories));
    }

    /**
     * 현재 로그인한 사용자가 등록한 요리 목록을 조회합니다. (내 레시피 조회)
     * RecipeCommandController의 인증 방식을 적용하여 경로 변수 대신 토큰 정보를 활용합니다.
     * GET /api/v1/recipes/my
     */
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getMyDishes(
            @AuthenticationPrincipal UserDetails userDetails) {

        // UserPrincipal에서 PK(userNo)를 꺼내 서비스로 전달
        List<DishDTO> dishes = recipeQueryService.findDishesByUsername(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success(dishes));
    }

    /**
     * 특정 사용자가 등록한 요리 목록을 조회합니다. (일반 조회)
     * GET /api/v1/recipes/users/{userNo}
     */
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getDishesByUser(@PathVariable int userNo) {
        List<DishDTO> dishes = recipeQueryService.findDishesByUserNo(userNo);
        return ResponseEntity.ok(ApiResponse.success(dishes));
    }

    /**
     * 특정 사용자가 등록한 요리의 상세 정보(레시피 포함)를 모두 조회합니다.
     * GET /api/v1/recipes/users/{userNo}/details
     */
    @GetMapping("/users/{userNo}/details")
    public ResponseEntity<ApiResponse<List<RecipeDetailResponse>>> getDishDetailsByUser(@PathVariable int userNo) {
        List<RecipeDetailResponse> response = recipeQueryService.findDetailsByUser(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 특정 사용자의 추천 레시피(rcd_recipe) 목록을 조회합니다.
     * GET /api/v1/recipes/recommends/users/{userNo}
     */
    @GetMapping("/recommends/users/{userNo}")
    public ResponseEntity<ApiResponse<List<RecommendRecipeDTO>>> getRecommendRecipesByUser(@PathVariable int userNo) {
        List<RecommendRecipeDTO> response = recipeQueryService.findRecommendRecipesByUser(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 특정 요리의 상세 정보와 조리법(레시피) 목록을 함께 조회합니다.
     * GET /api/v1/recipes/{dishNo}
     */
    @GetMapping("/{dishNo}")
    public ResponseEntity<ApiResponse<RecipeDetailResponse>> getRecipeDetail(@PathVariable int dishNo) {
        RecipeDetailResponse detail = recipeQueryService.getRecipeDetail(dishNo);

        if (detail.getDish() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ApiResponse.success(detail));
    }
}