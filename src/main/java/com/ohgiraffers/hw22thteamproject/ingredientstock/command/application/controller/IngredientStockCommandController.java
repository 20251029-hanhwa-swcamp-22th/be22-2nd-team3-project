package com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.dto.request.IngredientStockCreateRequest;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.dto.response.IngredientStockCreateResponse;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.service.IngredientStockCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class IngredientStockCommandController {

    private final IngredientStockCommandService ingredientStockCommandService;

    @PostMapping("/ingredient-stock")
    public ResponseEntity<ApiResponse<IngredientStockCreateResponse>> registIngredientStock(
            @CookieValue(name = "refreshToken") String refreshToken,
            @RequestBody IngredientStockCreateRequest ingredientStockCreateRequest
    ) {
        IngredientStockCreateResponse response = this.ingredientStockCommandService.registIngredientStock(refreshToken,ingredientStockCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

}
