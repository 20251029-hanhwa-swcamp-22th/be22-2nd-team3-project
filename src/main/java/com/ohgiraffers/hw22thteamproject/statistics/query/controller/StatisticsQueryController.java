package com.ohgiraffers.hw22thteamproject.statistics.query.controller;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
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
		Map<String, Object> response = statisticsQueryService.getMonthlyPurchaseDetails(userNo,yearMonth);
		return ResponseEntity.ok(ApiResponse.success(response));
	}

}