package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request;

import java.util.List;
import java.util.Set;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.DishCategoryEnum;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeRecommendRequest {
  private String dishName;                    // 추천받을 요리 이름
  private List<String> ingredients;           // 사용할 재료
  private List<String> dislikedIngredients;   // 못 먹는 재료
  private CookingLevel skillLevel;            // 요리 숙련도
  private List<String> tools;                 // 보유 조리 도구
  private Set<DishCategoryEnum> foodTypes;             // 원하는 음식 유형 (최대 3개)
  private String preference;                  // 취향
  private int servings;                       // 몇 인분

  public enum CookingLevel {
    BEGINNER, INTERMEDIATE, ADVANCED
  }
}