package com.ohgiraffers.hw22thteamproject.recipe.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.Instant; // Instant import 추가
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Recipe;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishCategoryRepository;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishRepository;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.RecipeRepository;
import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaRecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishCategoryRepository dishCategoryRepository;


    @Test
    @DisplayName("Recipe 저장 및 ID로 조회 테스트")
    void saveAndFindRecipeByIdTest() {
        // Given 1: User & Category 준비
        User user = userRepository.findByUserId("gusgh075")
            .orElseThrow(() -> new IllegalArgumentException("테스트용 유저가 DB에 없습니다."));

        DishCategory category = dishCategoryRepository.findById(1)
            .orElseThrow(() -> new IllegalArgumentException("테스트용 카테고리가 DB에 없습니다."));

        // Given 2: Dish 저장 (부모 엔티티)
        Dish dish = Dish.builder()
            .dishName("테스트용 탕수육")
            .dishImgFileRoute("/images/test.jpg")
            .dishIsMarked(false)
            .userNo(user)
            .dishCategoryNo(category)
            .build();
        Dish savedDish = dishRepository.save(dish);

        // Given 3: Recipe 생성
        // 주의: Recipe 엔티티의 createdAt, updatedAt은 @Builder.Default가 없으므로 직접 넣어줘야 합니다.
        Recipe recipe = Recipe.builder()
            .dishNo(savedDish)
            .recipeIngredient("{\"돼지고기\": \"300g\", \"전분\": \"100g\"}")
            .recipeCookery("1. 튀긴다. 2. 소스를 붓는다.")
            .build();

        // When: Recipe 저장
        Recipe savedRecipe = recipeRepository.save(recipe);

        // Then 1: 저장 검증
        assertThat(savedRecipe.getId()).isNotNull();

        // When: ID로 조회
        Optional<Recipe> foundRecipeOptional = recipeRepository.findById(savedRecipe.getId());

        // Then 2: 조회 검증
        assertThat(foundRecipeOptional).isPresent();
        Recipe foundRecipe = foundRecipeOptional.get();

        assertThat(foundRecipe.getDishNo().getDishName()).isEqualTo("테스트용 탕수육");
        assertThat(foundRecipe.getRecipeCookery()).contains("튀긴다");
    }
}