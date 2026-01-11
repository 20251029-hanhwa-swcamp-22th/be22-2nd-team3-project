package com.ohgiraffers.hw22thteamproject.statistics.query.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.CategoryPurchaseDTO;
import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.IngredientPurchaseDTO;
import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.MonthlyPurchaseDTO;
import com.ohgiraffers.hw22thteamproject.statistics.query.mapper.StatisticsMapper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsQueryService {

	private final StatisticsMapper statisticsMapper;

	public Map<String, Object> getMonthlyPurchaseDetails(int userNo, String yearMonth) {

		List<MonthlyPurchaseDTO> list = statisticsMapper.selectMonthlyPurchaseList(userNo, yearMonth);

		int totalCost = list.stream()
			.mapToInt(MonthlyPurchaseDTO::getCost)
			.sum();

		Map<String, Object> response = new HashMap<>();
		response.put("analysisList", list);  // 상세 내역
		response.put("totalCost", totalCost); // 총 합계

		return response;

	}

	public List<IngredientPurchaseDTO> getIngredientPurchase(int userNo){
		List<IngredientPurchaseDTO> list = statisticsMapper.selectIngredientPurchaseList(userNo);

		if (list.isEmpty()) {
			return list;
		}

		long total = list.stream()
			.mapToLong(IngredientPurchaseDTO::getTotalCost)
			.sum();

		for (IngredientPurchaseDTO dto : list) {
			if(total > 0){
				double percentage = (double) dto.getTotalCost() / total * 100;
				dto.setPercentage(Math.round(percentage * 100) / 100.0);
			}
		}
		return list;
	}

	public List<CategoryPurchaseDTO> getCategoryExpenseStats(int userNo) {
		return statisticsMapper.selectCategoryPurchaseList(userNo);
	}

}