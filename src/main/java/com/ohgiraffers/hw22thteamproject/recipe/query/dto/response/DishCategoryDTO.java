package com.ohgiraffers.hw22thteamproject.recipe.query.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DishCategoryDTO {
    private Integer dishCategoryNo;
    private String dishCategoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}