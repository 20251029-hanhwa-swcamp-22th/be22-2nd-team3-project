package com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Recipe;

public interface RecipeRepository {
  Recipe save(Recipe Recipe);

}
