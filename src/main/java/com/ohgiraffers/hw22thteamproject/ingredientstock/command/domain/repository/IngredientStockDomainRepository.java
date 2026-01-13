package com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.IngredientStock;

import java.util.List;
import java.util.Optional;

public interface IngredientStockDomainRepository {

    IngredientStock save(IngredientStock ingredientStock);

    Optional<IngredientStock> findByUser_UserNoAndIngredientStockNo(long userNo, long ingredientStockNo);

    List<IngredientStock> findAllByUser_UserNo(long userNo);
}