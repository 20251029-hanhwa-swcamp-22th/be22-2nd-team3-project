package com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.DishCategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class DishCreateRequest {
    private String dishName;
    private DishCategoryEnum dishCategoryEnum;
}
