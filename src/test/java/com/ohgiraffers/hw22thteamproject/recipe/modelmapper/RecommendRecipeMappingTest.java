package com.ohgiraffers.hw22thteamproject.recipe.modelmapper;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecipeRecommendResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.RecommendRecipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 실제 애플리케이션 컨텍스트를 로드하여 빈을 주입받음
class RecommendRecipeMappingTest {

  @Autowired
  private ModelMapper modelMapper; // main/java의 ModelMapperConfig에서 등록된 빈 주입

  @Test
  @DisplayName("등록된 ModelMapper 빈을 사용하여 DTO를 엔티티로 변환하는 통합 테스트")
  void testMappingWithRealBean() {
    // Given: 테스트용 데이터 준비
    RecipeRecommendResponse response = RecipeRecommendResponse.builder()
        .dishCategory(RecipeRecommendResponse.DishCategory.WESTERN)
        .dishName("AI 테스테이크")
        .ingredients(List.of(
            new RecipeRecommendResponse.IngredientDetail("소고기", 200.0, "g"),
            new RecipeRecommendResponse.IngredientDetail("로즈마리", 1.0, "줄기")
        ))
        .substitutions(List.of(
            new RecipeRecommendResponse.Substitution("버터", "올리브유")
        ))
        .cookery(List.of("1. 고기에 시즈닝을 한다", "2. 팬에 굽는다"))
        .tips(List.of("팬을 뜨겁게 달구세요"))
        .build();

    // When: 실제 빈을 통한 매핑 수행
    RecommendRecipe entity = modelMapper.map(response, RecommendRecipe.class);

    // Then: ModelMapperConfig에 정의된 컨버터 로직 검증
    assertAll(
        () -> assertNotNull(entity),
        () -> assertEquals("AI 테스테이크", entity.getRcdRecipeDishName()), // 필드명 매핑 확인
        () -> assertEquals(4, entity.getDishCategoryNo()), // WESTERN (ordinal 3) + 1 = 4 확인
        () -> assertEquals("소고기(200.0g), 로즈마리(1.0줄기)", entity.getRcdRecipeIngredients()), // 컨버터 확인
        () -> assertEquals("버터 -> 올리브유", entity.getRcdRecipeSubstitutions()), // 컨버터 확인
        () -> assertEquals("1. 고기에 시즈닝을 한다\n2. 팬에 굽는다", entity.getRcdRecipeCookery()), // 개행 문자 확인
        () -> assertEquals("팬을 뜨겁게 달구세요",entity.getRcdRecipeTips())
    );
  }
}