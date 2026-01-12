package com.ohgiraffers.hw22thteamproject.recipe.command.application.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeCreateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeUpdateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecipeRecommendResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.service.RecipeCommandService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeCommandController {

	private final RecipeCommandService recipeCommandService;

	/**
	 * 레시피 신규 등록
	 * POST /api/v1/recipes
	 * @AuthenticationPrincipal을 사용하여 로그인한 사용자의 정보를 가져옵니다.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<Integer>> registRecipe(
		@RequestBody @Valid RecipeCreateRequest request,
		@AuthenticationPrincipal UserDetails userDetails) {

		// 인증된 사용자의 ID(username)를 서비스로 전달
		Integer dishNo = recipeCommandService.registRecipe(request, userDetails.getUsername());

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
		@RequestBody @Valid RecipeUpdateRequest request,
		@AuthenticationPrincipal UserDetails userDetails) { // 수정 시에도 본인 확인이 필요할 수 있어 추가 권장

		// 서비스 메서드에 username을 전달하여 본인 확인 로직 추가 가능 (현재는 기존 로직 유지하되 확장성 고려)
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

	/**
	 * 레시피 추천
	 * @param request 레시피 추천 객체
	 * @return 레시피 추천 결과
	 */
	@PostMapping("/recommend")
	public ResponseEntity<RecipeRecommendResponse> recommendRecipe(
		@RequestBody RecipeRecommendRequest request,
		@AuthenticationPrincipal UserDetails userDetails) {

		// userDetails.getUsername()을 통해 로그인한 사용자 ID 전달
		RecipeRecommendResponse response = recipeCommandService.getRecipeRecommendation(request, userDetails.getUsername());
		return ResponseEntity.ok(response);
	}
}