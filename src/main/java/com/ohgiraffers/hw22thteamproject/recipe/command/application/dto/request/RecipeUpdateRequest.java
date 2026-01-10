package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RecipeUpdateRequest {
    private String dishName;
    private String dishImgFileRoute;
    private Integer dishCategoryNo;
    private List<RecipeStepRequest> recipes;

    @Getter
    @Setter
    public static class RecipeStepRequest {
        private String recipeIngredient;
        private String recipeCookery;
    }
}