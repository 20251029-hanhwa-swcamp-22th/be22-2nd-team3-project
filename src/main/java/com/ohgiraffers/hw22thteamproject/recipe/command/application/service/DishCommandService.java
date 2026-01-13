package com.ohgiraffers.hw22thteamproject.recipe.command.application.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.RecommendRecipe;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishCategoryRepository;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishRepository;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.RecipeRepository;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.RecommendRecipeRepository;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DishCommandService {

	private final RecommendRecipeRepository recommendRecipeRepository;
	private final DishRepository dishRepository;
	private final UserRepository userRepository;
	private final DishCategoryRepository dishCategoryRepository;
	private final RecipeRepository recipeRepository;
	private final ModelMapper modelMapper;

	@Transactional
	public DishDTO saveRecommendedToMyDish(Integer recommendRecipeNo, String username) {
		DishDTO resultDish;
		// 추천레시피 조회
		RecommendRecipe rcdRecipe = recommendRecipeRepository.findById(recommendRecipeNo)
			.orElseThrow(() -> new IllegalArgumentException("추천레시피 데이터 없음"));
		// 추천레시피의 이름으로 음식이 있는지 조회
		Optional<Dish> dish = dishRepository.findByDishName(rcdRecipe.getRcdRecipeDishName());
		// 음식이 없을 때 등록
		if (dish.isEmpty())
			resultDish = modelMapper.map(
				dishRepository.save(
					Dish.builder()
						.userNo(userRepository.findByUserId(username)
							.orElseThrow(() -> new IllegalArgumentException("유저 없음")))
						.dishCategoryNo(dishCategoryRepository.findById(rcdRecipe.getDishCategoryNo())
							.orElseThrow(() -> new IllegalArgumentException("카테고리 없음")))
						.dishName(rcdRecipe.getRcdRecipeDishName())
						.build()
				),
				DishDTO.class
			);
		// 음식이 있을 때 get
		else
			resultDish = modelMapper.map(dish.get(), DishDTO.class);
		return resultDish;
	}
}
