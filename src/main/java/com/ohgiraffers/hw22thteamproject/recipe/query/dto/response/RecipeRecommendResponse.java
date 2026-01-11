package com.ohgiraffers.hw22thteamproject.recipe.query.dto.response;

import java.util.List;

public record RecipeRecommendResponse(
	String dishCategory,                // 요리 유형
	String dishName,                  // 추천 요리 이름
	List<IngredientDetail> ingredients, // 필요한 재료 정보
	List<Substitution> substitutions,   // 대체 가능 재료
	List<String> cookery,          // 조리법 (단계별)
	List<String> tips                   // 조리 팁
) {
	public record IngredientDetail(
		String name,                    // 재료명
		double amount,                  // 계량
		String unit                     // 단위
	) {
	}

	public record Substitution(
		String original,                // 원래 재료
		String replacement              // 대체 재료
	) {
	}
}