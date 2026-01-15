package com.ohgiraffers.recipeservice.ingredientstock.query.dto.response;

import com.ohgiraffers.recipeservice.ingredientstock.command.domain.aggregate.IngredientStock;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class IngredientStockResponse {

    private final List<IngredientStock> ingredientStocks;

}
