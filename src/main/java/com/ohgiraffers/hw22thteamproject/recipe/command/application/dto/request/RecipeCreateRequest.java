package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeCreateRequest {
  @NotBlank(message = "요리 번호는 필수입니다.")
  private Integer dishNo;

  @NotBlank(message = "재료는 필수입니다.")
  private String ingredients;

  @NotBlank(message = "조리법 필수입니다.")
  private String recipeCookery;
}