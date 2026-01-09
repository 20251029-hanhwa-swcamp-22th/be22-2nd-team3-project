package com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Recipe;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.RecipeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRecipeRepository extends RecipeRepository, JpaRepository<Recipe,Long> {}
