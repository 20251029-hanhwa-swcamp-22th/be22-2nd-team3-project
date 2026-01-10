package com.ohgiraffers.hw22thteamproject.recipe.query.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
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
}