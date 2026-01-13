package com.ohgiraffers.hw22thteamproject.config;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecipeRecommendResponse;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.RecommendRecipe;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        configureRecommendRecipeMapping(modelMapper);
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(
                        org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
        return modelMapper;
    }

    /**
     * RecommendRecipe 전용 매핑 설정
     */
    private void configureRecommendRecipeMapping(ModelMapper modelMapper) {

        // 1. DishCategory(Enum) -> dishCategoryNo(Integer) 변환 컨버터
        // 순서(Ordinal)에 1을 더해 번호로 매핑
        Converter<com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.DishCategoryEnum, Integer> categoryConverter = context -> context
                .getSource() == null ? null : context.getSource().ordinal() + 1;

        // 2. Ingredients List -> String 변환 컨버터
        // 예: "당근(1.0개), 양파(0.5개)" 형태
        Converter<List<RecipeRecommendResponse.IngredientDetail>, String> ingredientsConverter = context -> context
                .getSource() == null ? null
                        : context.getSource().stream()
                                .map(ing -> String.format("%s(%.1f%s)", ing.getName(), ing.getAmount(), ing.getUnit()))
                                .collect(Collectors.joining(", "));

        // 3. Substitutions List -> String 변환 컨버터
        // 예: "고기 -> 버섯, 설탕 -> 올리고당" 형태
        Converter<List<RecipeRecommendResponse.Substitution>, String> substitutionConverter = context -> context
                .getSource() == null ? null
                        : context.getSource().stream()
                                .map(sub -> String.format("%s -> %s", sub.getOriginal(), sub.getReplacement()))
                                .collect(Collectors.joining(", "));

        // 4. Cookery & Tips List -> String 변환 컨버터 (줄바꿈으로 구분)
        Converter<List<String>, String> listToStringConverter = context -> context.getSource() == null ? null
                : String.join("\n", context.getSource());

        // TypeMap 설정
        modelMapper.createTypeMap(RecipeRecommendResponse.class, RecommendRecipe.class)
                .addMappings(mapper -> {
                    // (1) 카테고리 Enum -> 번호
                    mapper.using(categoryConverter)
                            .map(RecipeRecommendResponse::getDishCategoryEnum, RecommendRecipe::setDishCategoryNo);

                    // (2) 재료 리스트 -> 문자열
                    mapper.using(ingredientsConverter)
                            .map(RecipeRecommendResponse::getIngredients, RecommendRecipe::setRcdRecipeIngredients);

                    // (3) 대체 재료 리스트 -> 문자열
                    mapper.using(substitutionConverter)
                            .map(RecipeRecommendResponse::getSubstitutions, RecommendRecipe::setRcdRecipeSubstitutions);

                    // (4) 조리법 리스트 -> 문자열
                    mapper.using(listToStringConverter)
                            .map(RecipeRecommendResponse::getCookery, RecommendRecipe::setRcdRecipeCookery);

                    // (5) 팁 리스트 -> 문자열
                    mapper.using(listToStringConverter)
                            .map(RecipeRecommendResponse::getTips, RecommendRecipe::setRcdRecipeTips);

                    // (6) 요리이름 매핑
                    mapper.map(RecipeRecommendResponse::getDishName, RecommendRecipe::setRcdRecipeDishName);

                    // (7) 사용자 번호: 서비스 로직에서 직접 세팅하므로 매핑 제외
                    mapper.skip(RecommendRecipe::setUserNo);
                });
    }

}