package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response;

import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDTO;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class AdoptRecommendedResponse {
	RecipeDTO recipe;
	DishDTO dish;
}
