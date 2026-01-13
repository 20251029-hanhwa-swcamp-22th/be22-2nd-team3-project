package com.ohgiraffers.hw22thteamproject.recipe.command.application.service;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecipeRecommendResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.RecommendRecipe;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.RecommendRecipeRepository;
import com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.service.RecipeRecommendService;
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
	public Integer registRecipe(RecipeCreateRequest request, String username) { // username 파라미터 추가

		// 1. UserDetails에서 가져온 username(ID)으로 실제 DB 유저 정보 조회
		UserDTO userDTO = userMapper.selectUserByUserId(username);

		if (userDTO == null) {
			throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
		}

		// 2. 카테고리 조회
		DishCategoryDTO dishCategoryDTO = dishCategoryMapper.selectDishCategoryById(request.getDishCategoryNo())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

		// 3. DTO -> Entity 변환
		DishCategory dishCategoryEntity = modelMapper.map(dishCategoryDTO, DishCategory.class);
		User user = modelMapper.map(userDTO, User.class); // 조회된 유저 정보를 Entity로 변환

		// 4. Dish 생성 (연관관계 설정)
		Dish newDish = Dish.builder()
				.dishName(request.getDishName())
				.dishImgFileRoute(request.getDishImgFileRoute())
				.userNo(user) // DB에서 조회한 User Entity(PK 포함) 주입
				.dishCategoryNo(dishCategoryEntity)
				.build();

		// 5. Recipe 생성 및 Dish에 추가
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

		// update는 JPA 영속성 컨텍스트에 의해 트랜잭션 종료 시 자동 반영됨 (Dirty Checking)
	}

	@Transactional
	public void deleteRecipe(Integer dishNo) {
		dishRepository.deleteById(dishNo);
	}

	@Transactional
	public RecipeRecommendResponse getRecipeRecommendation(RecipeRecommendRequest request, String username) {
		// 1. AI 추천 서비스 호출
		RecipeRecommendResponse recipeRecommendation = recipeRecommendService.getRecipeRecommendation(request);

		// 2. 추천 결과 저장용 엔티티 변환
		RecommendRecipe recommendRecipe = modelMapper.map(recipeRecommendation, RecommendRecipe.class);

		// 3. UserDetails에서 가져온 username으로 유저 조회
		UserDTO userDTO = userMapper.selectUserByUserId(username);
		if (userDTO == null) {
			throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
		}

		// 4. 조회된 유저의 PK(userNo)를 설정
		User user = modelMapper.map(userDTO, User.class);

		// RecommendRecipe 엔티티의 userNo 필드 타입에 맞게 설정 (int로 가정)
		recommendRecipe.setUserNo(user.getUserNo().intValue());

		recommendRecipeRepository.save(recommendRecipe);
		return recipeRecommendation;
	}

	@Transactional
	public Integer registRecipeFromRecommend(Integer rcdId, String username) {
		// 1. 추천 레시피 조회
		RecommendRecipe rcd = recommendRecipeRepository.findById(rcdId)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 추천 레시피입니다."));

		// 2. 사용자 조회
		UserDTO userDTO = userMapper.selectUserByUserId(username);
		if (userDTO == null) {
			throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
		}
		User user = modelMapper.map(userDTO, User.class);

		// 3. 카테고리 조회
		DishCategoryDTO dishCategoryDTO = dishCategoryMapper.selectDishCategoryById(rcd.getDishCategoryNo())
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
		DishCategory dishCategory = modelMapper.map(dishCategoryDTO, DishCategory.class);

		// 4. Dish 생성
		Dish newDish = Dish.builder()
				.dishName(rcd.getRcdRecipeDishName())
				.dishImgFileRoute("default.jpg") // 이미지가 없으므로 기본값 설정
				.userNo(user)
				.dishCategoryNo(dishCategory)
				.build();

		// 5. Recipe 생성 및 추가
		String fullCookery = rcd.getRcdRecipeCookery();
		if (rcd.getRcdRecipeTips() != null && !rcd.getRcdRecipeTips().isEmpty()) {
			fullCookery += "\n\nTip: " + rcd.getRcdRecipeTips();
		}

		Recipe recipe = Recipe.builder()
				.dishNo(newDish)
				.recipeIngredient(rcd.getRcdRecipeIngredients())
				.recipeCookery(fullCookery)
				.build();
		newDish.getRecipes().add(recipe);

		// 6. 저장
		Dish savedDish = dishRepository.save(newDish);
		return savedDish.getId();
	}
}