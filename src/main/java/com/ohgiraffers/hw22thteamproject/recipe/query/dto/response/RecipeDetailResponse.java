package com.ohgiraffers.hw22thteamproject.recipe.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 요리 상세 조회 응답을 위한 DTO입니다.
 * 요리 기본 정보(Dish)와 하위 레시피(Recipe) 목록을 포함합니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetailResponse {

    private DishDTO dish;               // 요리 기본 정보 (이름, 이미지 경로, 카테고리 등)
    private List<RecipeDTO> recipes;    // 해당 요리의 상세 레시피/조리법 목록

    /**
     * 정적 팩토리 메서드 (선택 사항)
     * 서비스 계층에서 객체를 생성할 때 가독성을 높여줍니다.
     */
    public static RecipeDetailResponse of(DishDTO dish, List<RecipeDTO> recipes) {
        return new RecipeDetailResponse(dish, recipes);
    }
}