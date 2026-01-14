package com.ohgiraffers.hw22thteamproject.recipe.query.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendRecipeDTO {
    private Integer id;
    private Integer userNo;
    private Integer dishCategoryNo;
    private String rcdRecipeDishName;
    private String rcdRecipeIngredients;
    private String rcdRecipeSubstitutions;
    private String rcdRecipeCookery;
    private String rcdRecipeTips;
}
