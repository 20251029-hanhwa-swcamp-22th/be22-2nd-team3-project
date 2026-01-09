package com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;

public interface DishRepository {
  Dish save(Dish dish);
}
