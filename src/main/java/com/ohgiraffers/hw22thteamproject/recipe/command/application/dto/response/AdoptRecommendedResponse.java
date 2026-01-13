package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response;

import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdoptRecommendedResponse {
	private RecipeDTO recipe;
	private DishDTO dish;
}
