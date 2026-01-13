package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response;

import java.util.List;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.DishCategoryEnum;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendRecipeResponse {
  private DishCategoryEnum dishCategoryEnum;          // 요리 카테고리
  private String dishName;                    // 추천 요리 이름
  private List<IngredientDetail> ingredients; // 필요한 재료 정보
  private List<Substitution> substitutions;   // 대체 가능 재료
  private List<String> cookery;               // 조리법 (단계별)
  private List<String> tips;                  // 조리 팁

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class IngredientDetail {
    private String name;                    // 재료명
    private double amount;                  // 계량
    private String unit;                    // 단위
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  public static class Substitution {
    private String original;                // 원래 재료
    private String replacement;          // 대체 재료
  }
}