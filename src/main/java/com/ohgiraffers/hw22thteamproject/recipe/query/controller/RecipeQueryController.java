package com.ohgiraffers.hw22thteamproject.recipe.query.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishCategoryDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDetailResponse;
import com.ohgiraffers.hw22thteamproject.recipe.query.service.RecipeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 레시피 조회 전용 REST API 컨트롤러
 */
@RestController
@RequestMapping("/api/v1/recipes") // 복수형 명사 사용 및 버전 관리(v1)
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
     * 특정 사용자가 등록한 요리 목록을 조회합니다.
     * GET /api/v1/recipes/users/{userNo}
     */
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ApiResponse<List<DishDTO>>> getDishesByUser(@PathVariable int userNo) {
        List<DishDTO> dishes = recipeQueryService.findDishesByUser(userNo);
        return ResponseEntity.ok(ApiResponse.success(dishes));
    }

    /**
     * 특정 요리의 상세 정보와 조리법(레시피) 목록을 함께 조회합니다.
     * GET /api/v1/recipes/{dishNo}
     */
    @GetMapping("/{dishNo}")
    public ResponseEntity<ApiResponse<RecipeDetailResponse>> getRecipeDetail(@PathVariable int dishNo) {
        RecipeDetailResponse detail = recipeQueryService.getRecipeDetail(dishNo);

        if (detail.getDish() == null) {
            // 요리 정보가 없을 경우에 대한 예외 처리는 GlobalExceptionHandler에서 처리하거나 여기서 정의 가능
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ApiResponse.success(detail));
    }
}