package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.DishCategoryEnum;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.RecipeIngredient;

@Getter
@Setter
public class RecipeUpdateRequest {

    private Integer recipeNo;
    private List<RecipeIngredient> ingredients;
    private List<String> cookery;

}