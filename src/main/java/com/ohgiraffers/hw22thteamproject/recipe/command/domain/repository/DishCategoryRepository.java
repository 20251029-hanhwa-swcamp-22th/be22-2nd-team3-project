package com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;

public interface DishCategoryRepository {
  DishCategory save(DishCategory dishCategory);
}
