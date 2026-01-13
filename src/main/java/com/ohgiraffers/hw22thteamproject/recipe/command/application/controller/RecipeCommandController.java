package com.ohgiraffers.hw22thteamproject.recipe.command.application.controller;

import java.util.HashSet;
import java.util.Set;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeCreateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeUpdateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.AdoptRecommendedResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecommendRecipeResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.service.DishCommandService;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.service.RecipeCommandService;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDTO;

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
	private final DishCommandService dishCommandService;

	/**
	 * 레시피 신규 등록
	 * POST /api/v1/recipes
	 * 
	 * @AuthenticationPrincipal을 사용하여 로그인한 사용자의 정보를 가져옵니다.
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<Integer>> registRecipe(
			@RequestBody @Valid RecipeCreateRequest request,
			@AuthenticationPrincipal UserDetails userDetails) {

		Integer recipeNo = recipeCommandService.registRecipe(request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ApiResponse.success(recipeNo));
	}

	/**
	 * 레시피 수정
	 * PUT /api/v1/recipes/{dishNo}
	 */
	@PutMapping("/update")
	public ResponseEntity<ApiResponse<RecipeDTO>> updateRecipe(
			@RequestBody @Valid RecipeUpdateRequest request,
			@AuthenticationPrincipal UserDetails userDetails) { // 수정 시에도 본인 확인이 필요할 수 있어 추가 권장

		// 서비스 메서드에 username을 전달하여 본인 확인 로직 추가 가능 (현재는 기존 로직 유지하되 확장성 고려)
		RecipeDTO recipeDTO = recipeCommandService.updateRecipe(request);

		return ResponseEntity.ok(ApiResponse.success(recipeDTO));
	}

	/**
	 * 레시피 삭제
	 * DELETE /api/v1/recipes/delete
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<ApiResponse<Void>> deleteRecipe(@PathVariable Integer recipeNo) {

		recipeCommandService.deleteRecipe(recipeNo);

		return ResponseEntity.ok(ApiResponse.success(null));
	}

	/**
	 * 레시피 추천
	 * 
	 * @param request 레시피 추천 객체
	 * @return 레시피 추천 결과
	 */
	@PostMapping("/recommend")
	public ResponseEntity<ApiResponse<RecommendRecipeResponse>> recommendRecipe(
			@RequestBody RecipeRecommendRequest request,
			@AuthenticationPrincipal UserDetails userDetails) {

		// userDetails.getUsername()을 통해 로그인한 사용자 ID 전달
		RecommendRecipeResponse response = recipeCommandService.getRecipeRecommendation(request,
				userDetails.getUsername());
		return ResponseEntity.ok(ApiResponse.success(response));
	}

	/**
	 * 추천 레시피를 유저 레시피로 등록
	 * 
	 * @param recommendRecipeNo 추천 레시피 번호
	 * @param userDetails       유저 정보
	 * @return 저장된 recipe와 dish 정보
	 */
	@GetMapping("/recommend/save")
	public ResponseEntity<ApiResponse<AdoptRecommendedResponse>> adoptRecommendedRecipe(
			@RequestParam Integer recommendRecipeNo,
			@AuthenticationPrincipal UserDetails userDetails) {
		DishDTO responseDish = dishCommandService.saveRecommendedToMyDish(recommendRecipeNo, userDetails.getUsername());
		RecipeDTO responseRecipe = recipeCommandService.saveRecommendedToMyRecipe(recommendRecipeNo,
				responseDish.getDishNo());
		AdoptRecommendedResponse adoptRecommendedResponse = new AdoptRecommendedResponse(responseRecipe, responseDish);
		return ResponseEntity.ok(ApiResponse.success(adoptRecommendedResponse));
	}
}