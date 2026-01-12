package com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.service;

import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.hw22thteamproject.recipe.command.application.dto.response.RecipeRecommendResponse;

@Service
public class RecipeRecommendService {

	private final ChatClient chatClient;

	@Value("classpath:/prompts/recipe-recommend-user.st")
	private Resource userPromptResource;

	@Value("classpath:/prompts/recipe-recommend-system.st")
	private Resource systemPromptResource;

	public RecipeRecommendService(ChatClient.Builder builder) {
		// 기본 설정을 빌더를 통해 구성
		this.chatClient = builder.build();
	}

	public RecipeRecommendResponse getRecipeRecommendation(RecipeRecommendRequest request) {
		return chatClient.prompt()
			// 시스템 프롬프트 설정 (전문 요리사 페르소나 등)
			.system(s -> s.text(systemPromptResource))
			// 유저 프롬프트 설정 및 리소스 바인딩
			.user(u -> u.text(userPromptResource)
				.params(Map.of(
					"dishName", request.dishName() != null ? request.dishName() : "재료에 어울리는 요리",
					"ingredients", request.ingredients(),
					"dislikedIngredients", request.dislikedIngredients(),
					"skillLevel", request.skillLevel().name(),
					"tools", request.tools(),
					"foodTypes", request.foodTypes(),
					"preference", request.preference(),
					"servings", request.servings()
				))
			)
			.call()
			// 응답을 원하는 객체(Record)로 자동 매핑 (Structured Output)
			.entity(RecipeRecommendResponse.class);
	}
}