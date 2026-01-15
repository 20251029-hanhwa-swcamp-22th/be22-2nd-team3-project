package com.ohgiraffers.recipeservice.ingredientstock.query.controller;

import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.ingredientstock.query.dto.response.IngredientStockResponse;
import com.ohgiraffers.recipeservice.ingredientstock.query.service.IngredientStockQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class IngredientStockQueryController {

    private final IngredientStockQueryService ingredientStockQueryService;

    /* UserNo에 맞는 Ingredient stock 전부 조회 */
    @GetMapping("/ingredient-stocks")
    public ResponseEntity<ApiResponse<IngredientStockResponse>> getIngredientStockList(
            @CookieValue(name = "refreshToken") String refreshToken
    ){
        IngredientStockResponse response = this.ingredientStockQueryService.getIngredientStockList(refreshToken);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
