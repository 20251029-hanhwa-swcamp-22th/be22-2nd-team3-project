package com.ohgiraffers.hw22thteamproject.recipe.command.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeCreateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.service.RecipeCommandService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeCommandController {

	private final RecipeCommandService recipeCommandService;

	/**
	 * 새로운 요리 및 레시피 등록
	 * POST /api/v1/recipes
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<Integer>> registRecipe(@RequestBody @Valid RecipeCreateRequest request) {
		Integer dishNo = recipeCommandService.registRecipe(request);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success(dishNo));
	}
}