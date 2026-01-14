package com.ohgiraffers.hw22thteamproject.recipe.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {
    private Integer dishNo;
    private Integer userNo;
    private Integer dishCategoryNo;
    private String dishName;
    private String dishImgFileRoute;
    private boolean dishIsMarked;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static DishDTO from(Dish dish){
        return DishDTO.builder()
            .dishNo(dish.getId())
            .userNo(dish.getUserNo().getUserNo().intValue())
            .dishCategoryNo(dish.getDishCategoryNo().getId())
            .dishName(dish.getDishName())
            .dishImgFileRoute(dish.getDishImgFileRoute())
            .dishIsMarked(dish.getDishIsMarked())
            .createdAt(dish.getCreatedAt())
            .updatedAt(dish.getUpdatedAt())
            .build();
    }
}