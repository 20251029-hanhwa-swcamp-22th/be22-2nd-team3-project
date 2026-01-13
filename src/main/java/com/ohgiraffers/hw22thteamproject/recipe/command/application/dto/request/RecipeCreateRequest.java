package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.DishCategoryEnum;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.RecipeIngredient;

@Getter
@Setter
public class RecipeCreateRequest {
    @NotBlank(message = "요리 이름은 필수입니다.")
    private String dishName;

    @NotNull(message = "재료는 필수입니다.")
    private List<RecipeIngredient> ingredients;

    @NotNull(message = "조리법은 필수입니다.")
    private List<String> cookery;


}