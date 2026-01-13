package com.ohgiraffers.hw22thteamproject.ingredientstock.command.infrastructure.repository;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.IngredientStock;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.repository.IngredientStockDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIngredientStockDomainRepository extends JpaRepository<IngredientStock, Long>, IngredientStockDomainRepository {

}
