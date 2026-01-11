package com.ohgiraffers.hw22thteamproject.statistics.query.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.MonthlyPurchaseDTO;
import com.ohgiraffers.hw22thteamproject.statistics.query.mapper.StatisticsMapper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsQueryService {

	private final StatisticsMapper statisticsMapper;

	public List<MonthlyPurchaseDTO> getMonthlyPurchaseDetails(int userNo, String yearMonth) {
		return statisticsMapper.findMonthlyPurchaseDetails(userNo, yearMonth); // ✅
	}

}