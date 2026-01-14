package com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository;

import java.util.Optional;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Recipe;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.RecommendRecipe;

public interface RecommendRecipeRepository {

  RecommendRecipe save(RecommendRecipe Recipe);
  Optional<RecommendRecipe> findById(Integer id);
  void deleteById(Integer id);

}
