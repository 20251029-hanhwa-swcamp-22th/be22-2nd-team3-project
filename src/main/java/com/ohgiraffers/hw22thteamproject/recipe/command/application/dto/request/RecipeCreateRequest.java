package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RecipeCreateRequest {
    @NotBlank(message = "요리 이름은 필수입니다.")
    private String dishName;
    
    @NotBlank(message = "이미지 경로는 필수입니다.")
    private String dishImgFileRoute;
    
    @NotNull(message = "카테고리 번호는 필수입니다.")
    private Integer dishCategoryNo;
    
    @NotNull(message = "사용자 번호는 필수입니다.")
    private Long userNo;

    private List<RecipeStepRequest> recipes; // 조리 단계 목록

    @Getter
    @Setter
    public static class RecipeStepRequest {
        private String recipeIngredient;
        private String recipeCookery;
    }
}