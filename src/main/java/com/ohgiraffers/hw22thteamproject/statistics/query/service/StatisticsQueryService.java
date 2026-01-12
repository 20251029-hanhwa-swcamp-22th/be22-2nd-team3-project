package com.ohgiraffers.hw22thteamproject.statistics.query.service;

import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public DisposalCostResponse getDisposalHistory(int userNo, String startDate, String endDate) {

		List<DisposalHistoryDTO> list = statisticsMapper.selectDisposalHistoryList(userNo, startDate, endDate);

		long totalCost = 0;

		totalCost = list.stream().mapToLong(DisposalHistoryDTO::getDisposalCost)
			.sum();

		return DisposalCostResponse.builder()
			.totalDisposalCost(totalCost)
			.build();
	}

	public List<MonthlyDisposalDTO> getMonthlyDisposalList(int userNo) {
    List<MonthlyDisposalDTO> list = statisticsMapper.selectMonthlyDisposalList(userNo);

    List<MonthlyDisposalDTO> resultList = new ArrayList<>();

    Map<String, MonthlyDisposalDTO> listMap = list.stream().collect(Collectors.toMap(MonthlyDisposalDTO::getStatMonth, dto -> dto));

    LocalDate currentMonth = LocalDate.now();
    for (int i = 5; i >= 0; i--) {

      LocalDate lastMonth = currentMonth.minusMonths(i);
      String key = lastMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));

      if (listMap.containsKey(key)) {
        resultList.add(listMap.get(key));
      } else {
        MonthlyDisposalDTO emptyList = new MonthlyDisposalDTO();
        emptyList.setStatMonth(key);
        emptyList.setTotalCost(0L);
        emptyList.setDiscardCost(0L);
        emptyList.setWasteRate(0.0);
        resultList.add(emptyList);
      }
    }
    return resultList;
  }

}