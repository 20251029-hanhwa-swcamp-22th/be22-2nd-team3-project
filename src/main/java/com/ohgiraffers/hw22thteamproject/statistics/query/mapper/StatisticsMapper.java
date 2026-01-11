package com.ohgiraffers.hw22thteamproject.statistics.query.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.IngredientPurchaseDTO;
import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.MonthlyPurchaseDTO;

@Mapper
public interface StatisticsMapper {
	List<MonthlyPurchaseDTO> selectMonthlyPurchaseList(
		@Param("userNo") int userNo,
		@Param("yearMonth") String yearMonth);

	List<IngredientPurchaseDTO> selectIngredientPurchaseList(
		@Param("userNo") int userNo);
}