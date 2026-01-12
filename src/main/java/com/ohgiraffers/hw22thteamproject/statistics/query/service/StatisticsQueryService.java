package com.ohgiraffers.hw22thteamproject.statistics.query.service;

import com.ohgiraffers.hw22thteamproject.statistics.query.dto.request.DisposalCostRequest;
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

	/**
	 * 월별 지출
	 * @param userNo
	 * @param yearMonth
	 * @return
	 */
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

	/**
	 * 삭자재별 구매내역
	 * @param userNo
	 * @return
	 */
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

	/**
	 * 카테고리별
	 * @param userNo
	 * @return
	 */
	public List<CategoryPurchaseDTO> getCategoryExpenseStats(int userNo) {
		return statisticsMapper.selectCategoryPurchaseList(userNo);
	}

	/**
	 * 기간내폐기조회
	 * @param userNo
	 * @param disposalCostRequest
	 * @return
	 */
	public DisposalCostResponse getDisposalCost(int userNo, DisposalCostRequest disposalCostRequest) {

		List<DisposalCostDTO> list = statisticsMapper.selectDisposalCostList(userNo, disposalCostRequest);

		long totalCost = 0;

		totalCost = list.stream().mapToLong(DisposalCostDTO::getDisposalCost)
			.sum();

		return DisposalCostResponse.builder()
			.totalDisposalCost(totalCost)
			.build();
	}

	/**
	 * 월별 폐기
	 * @param userNo
	 * @return
	 */
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