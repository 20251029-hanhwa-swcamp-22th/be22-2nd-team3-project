package com.ohgiraffers.hw22thteamproject.recipe.query.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RecipeDTO {
	private Integer recipeNo;
	private Integer dishNo;
	private String recipeIngredient; // JSON 형태의 문자열
	private String recipeCookery;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}