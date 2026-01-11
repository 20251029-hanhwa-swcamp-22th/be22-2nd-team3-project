package com.ohgiraffers.hw22thteamproject.statistics.query.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.MonthlyPurchaseDTO;

@Mapper
public interface StatisticsMapper {
	List<MonthlyPurchaseDTO> findMonthlyPurchaseDetails(
		@Param("userNo") int userNo,
		@Param("yearMonth") String yearMonth);
	}