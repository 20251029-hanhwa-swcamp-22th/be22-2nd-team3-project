package com.ohgiraffers.hw22thteamproject.statistics.query.controller;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.CategoryPurchaseDTO;
import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.IngredientPurchaseDTO;
import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.MonthlyPurchaseDTO;
import com.ohgiraffers.hw22thteamproject.statistics.query.service.StatisticsQueryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StatisticsQueryController {

	private final StatisticsQueryService statisticsQueryService;

	@GetMapping("/statistics/monthly/{userNo}")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getMonthlyPurchaseByUser(
		@PathVariable int userNo,
		@RequestParam String yearMonth
	) {
		Map<String, Object> response = statisticsQueryService.getMonthlyPurchaseDetails(userNo, yearMonth);
		return ResponseEntity.ok(ApiResponse.success(response));
	}

	@GetMapping("/statistics/ingreient/{userNo}")
	public ResponseEntity<ApiResponse<List<IngredientPurchaseDTO>>> getIngredientPurchaseByUser(
		@PathVariable int userNo
	) {
		List<IngredientPurchaseDTO> result = statisticsQueryService.getIngredientPurchase(userNo);
		return ResponseEntity.ok(ApiResponse.success(result));
	}
	
	@GetMapping("/statistics/category/{userNo}")
	public ResponseEntity<ApiResponse<List<CategoryPurchaseDTO>>> getCategoryStats(
		@PathVariable int userNo
	) {
		List<CategoryPurchaseDTO> result = statisticsQueryService.getCategoryExpenseStats(userNo);

		return ResponseEntity.ok(ApiResponse.success(result));
	}
	
}