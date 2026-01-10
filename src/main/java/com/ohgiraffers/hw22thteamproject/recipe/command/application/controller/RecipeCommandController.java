package com.ohgiraffers.hw22thteamproject.recipe.command.application.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeCreateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeUpdateRequest;
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
	 * 레시피 신규 등록
	 * POST /api/v1/recipes
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<Integer>> registRecipe(@RequestBody @Valid RecipeCreateRequest request) {

		Integer dishNo = recipeCommandService.registRecipe(request);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(ApiResponse.success(dishNo));
	}

	/**
	 * 레시피 수정
	 * PUT /api/v1/recipes/{dishNo}
	 */
	@PutMapping("/{dishNo}")
	public ResponseEntity<ApiResponse<Void>> updateRecipe(
		@PathVariable Integer dishNo,
		@RequestBody @Valid RecipeUpdateRequest request) {

		recipeCommandService.updateRecipe(dishNo, request);

		return ResponseEntity.ok(ApiResponse.success(null));
	}

	/**
	 * 레시피 삭제
	 * DELETE /api/v1/recipes/{dishNo}
	 */
	@DeleteMapping("/{dishNo}")
	public ResponseEntity<ApiResponse<Void>> deleteRecipe(@PathVariable Integer dishNo) {

		recipeCommandService.deleteRecipe(dishNo);

		return ResponseEntity.ok(ApiResponse.success(null));
	}
}