package com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.IngredientStock;

public interface IngredientStockDomainRepository {

    IngredientStock save(IngredientStock ingredientStock);

}
