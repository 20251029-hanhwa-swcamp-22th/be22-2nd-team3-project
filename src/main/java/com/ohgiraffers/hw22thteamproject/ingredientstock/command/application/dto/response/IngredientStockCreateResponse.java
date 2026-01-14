package com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.dto.response;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.IngredientStock;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IngredientStockCreateResponse {

    private final IngredientStock ingredientStock;

}
