package com.ohgiraffers.hw22thteamproject.recipe.command.application.service;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecipeRecommendResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.RecommendRecipe;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.RecommendRecipeRepository;
import com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.service.RecipeRecommendService;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeCreateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeUpdateRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Recipe;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishRepository;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishCategoryDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import com.ohgiraffers.hw22thteamproject.recipe.query.mapper.DishCategoryMapper;
import com.ohgiraffers.hw22thteamproject.recipe.query.mapper.DishMapper;
import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
import com.ohgiraffers.hw22thteamproject.user.query.dto.response.UserDTO;
import com.ohgiraffers.hw22thteamproject.user.query.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeCommandService {

	private final DishRepository dishRepository;
	private final DishCategoryMapper dishCategoryMapper;
	private final DishMapper dishMapper;
	private final UserMapper userMapper;
	private final ModelMapper modelMapper;
  private final RecipeRecommendService recipeRecommendService;
  private final RecommendRecipeRepository recommendRecipeRepository;
	@Transactional
	public Integer registRecipe(RecipeCreateRequest request) {

		UserDTO userDTO = userMapper.selectUserByUserId(request.getUserId());
		// .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

		DishCategoryDTO dishCategoryDTO = dishCategoryMapper.selectDishCategoryById(request.getDishCategoryNo())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

		DishCategory dishCategoryEntity = modelMapper.map(dishCategoryDTO, DishCategory.class);
		User user = modelMapper.map(userDTO, User.class);

		Dish newDish = Dish.builder()
			.dishName(request.getDishName())
			.dishImgFileRoute(request.getDishImgFileRoute())
			.userNo(user)
			.dishCategoryNo(dishCategoryEntity)
			.build();

		if (request.getRecipes() != null) {
			request.getRecipes().forEach(step -> {
				Recipe recipe = Recipe.builder()
					.dishNo(newDish)
					.recipeIngredient(step.getRecipeIngredient())
					.recipeCookery(step.getRecipeCookery())
					.build();
				newDish.getRecipes().add(recipe);
			});
		}

		Dish savedDish = dishRepository.save(newDish);

		return savedDish.getId();
	}

	@Transactional
	public void updateRecipe(Integer dishNo, RecipeUpdateRequest request) {

		DishDTO dishDTO = dishMapper.selectDishById(dishNo)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레시피입니다."));

		DishCategoryDTO dishCategoryDTO = dishCategoryMapper.selectDishCategoryById(request.getDishCategoryNo())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

		DishCategory dishCategoryEntity = modelMapper.map(dishCategoryDTO, DishCategory.class);
		Dish dishEntity = modelMapper.map(dishDTO, Dish.class);

		dishEntity.setDishName(request.getDishName());
		dishEntity.setDishImgFileRoute(request.getDishImgFileRoute());
		dishEntity.setDishCategoryNo(dishCategoryEntity);

		dishEntity.getRecipes().clear();
		if (request.getRecipes() != null) {
			request.getRecipes().forEach(step -> {
				Recipe recipe = Recipe.builder()
					.dishNo(dishEntity)
					.recipeIngredient(step.getRecipeIngredient())
					.recipeCookery(step.getRecipeCookery())
					.build();
				dishEntity.getRecipes().add(recipe);
			});
		}
	}

	@Transactional
	public void deleteRecipe(Integer dishNo) {

		dishRepository.deleteById(dishNo);

	}

  @Transactional
  public RecipeRecommendResponse getRecipeRecommendation(RecipeRecommendRequest request, String username) {
    RecipeRecommendResponse recipeRecommendation = recipeRecommendService.getRecipeRecommendation(request);

    RecommendRecipe recommendRecipe = modelMapper.map(recipeRecommendation,RecommendRecipe.class);
    User user = modelMapper.map(userMapper.selectUserByUserId(username), User.class);
    recommendRecipe.setUserNo(user.getUserNo().intValue());

    recommendRecipeRepository.save(recommendRecipe);
    return recipeRecommendation;
  }
}