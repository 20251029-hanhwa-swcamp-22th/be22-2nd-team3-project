package com.ohgiraffers.hw22thteamproject.recipe.query.service;

import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishCategoryDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.RecipeDetailResponse;
import com.ohgiraffers.hw22thteamproject.recipe.query.mapper.DishCategoryMapper;
import com.ohgiraffers.hw22thteamproject.recipe.query.mapper.DishMapper;
import com.ohgiraffers.hw22thteamproject.recipe.query.mapper.RecipeMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeQueryService {

	private final DishCategoryMapper dishCategoryMapper;
	private final DishMapper dishMapper;
	private final RecipeMapper recipeMapper;

	public List<DishCategoryDTO> findAllCategories() {
		return dishCategoryMapper.selectAllDishCategories();
	}

	public List<DishDTO> findDishesByUser(int userNo) {
		return dishMapper.selectDishesByUser(userNo);
	}

	public RecipeDetailResponse getRecipeDetail(int dishNo) {
		DishDTO dish = dishMapper.selectDishById(dishNo)
			.orElseThrow(() -> new RuntimeException("해당 아이디의 음식을 찾을 수 없습니다."));
		List<RecipeDTO> recipes = recipeMapper.selectRecipeByDishId(dishNo);

		return new RecipeDetailResponse(dish, recipes);
	}
}