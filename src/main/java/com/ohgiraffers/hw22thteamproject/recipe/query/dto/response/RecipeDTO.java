package com.ohgiraffers.hw22thteamproject.recipe.query.dto.response;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private Integer recipeNo;
    private Integer dishNo;
    private String recipeIngredient;
    private String recipeCookery;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static RecipeDTO from(Recipe recipe){
        return RecipeDTO.builder()
            .recipeNo(recipe.getId())
            .dishNo(recipe.getDishNo().getId())
            .recipeIngredient(recipe.getRecipeIngredient())
            .recipeCookery(recipe.getRecipeCookery())
            .createdAt(recipe.getCreatedAt())
            .updatedAt(recipe.getUpdatedAt())
            .build();
    }
}