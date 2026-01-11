package com.ohgiraffers.hw22thteamproject.recipe.query.controller;

import com.ohgiraffers.hw22thteamproject.recipe.query.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeRecommendResponse;
import com.ohgiraffers.hw22thteamproject.recipe.query.service.RecipeQueryService;
import com.ohgiraffers.hw22thteamproject.recipe.query.service.RecipeRecommendService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeRecommendController {

	private final RecipeRecommendService recipeRecommendService;

	@PostMapping("/recommend")
	public ResponseEntity<RecipeRecommendResponse> recommendRecipe(@RequestBody RecipeRecommendRequest request) {
		RecipeRecommendResponse response = recipeRecommendService.getRecipeRecommendation(request);
		return ResponseEntity.ok(response);
	}
}