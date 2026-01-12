package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request;

import java.util.List;

public record RecipeRecommendRequest(
    String dishName,                    // 추천받을 요리 이름
    List<String> ingredients,           // 사용할 재료
    List<String> dislikedIngredients,   // 못 먹는 재료
    CookingLevel skillLevel,            // 요리 숙련도 (Enum 권장)
    List<String> tools,                 // 보유 조리 도구
    List<String> foodTypes,             // 원하는 음식 유형 (최대 3개)
    String preference,                  // 취향
    int servings                        // 몇 인분
) {
    public enum CookingLevel {
        BEGINNER, INTERMEDIATE, ADVANCED
    }
}