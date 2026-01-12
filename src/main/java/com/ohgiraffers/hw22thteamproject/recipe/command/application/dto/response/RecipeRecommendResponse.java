package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response;

import java.util.List;

public record RecipeRecommendResponse(
	DishCategory dishCategory,          // 내부 Enum 사용
	String dishName,                    // 추천 요리 이름
	List<IngredientDetail> ingredients, // 필요한 재료 정보
	List<Substitution> substitutions,   // 대체 가능 재료
	List<String> cookery,               // 조리법 (단계별)
	List<String> tips                   // 조리 팁
) {
	/**
	 * 요리 카테고리 Enum
	 */
	public enum DishCategory {
		KOREAN,     // 한식
		CHINESE,    // 중식
		JAPANESE,   // 일식
		WESTERN,    // 양식
		SNACK,      // 분식/간식
		SALAD,      // 샐러드/다이어트
		FUSION,     // 퓨전
		ETC         // 기타
	}

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