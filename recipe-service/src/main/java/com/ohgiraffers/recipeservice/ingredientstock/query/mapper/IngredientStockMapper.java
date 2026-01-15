package com.ohgiraffers.recipeservice.ingredientstock.query.mapper;

import com.ohgiraffers.recipeservice.ingredientstock.command.domain.aggregate.IngredientStock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IngredientStockMapper {

    List<IngredientStock> getStocksByUserNo(long userNo);
}
