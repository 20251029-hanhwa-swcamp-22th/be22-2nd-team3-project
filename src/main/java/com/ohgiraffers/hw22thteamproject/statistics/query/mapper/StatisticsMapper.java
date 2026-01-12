package com.ohgiraffers.hw22thteamproject.statistics.query.mapper;

import java.util.List;

import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StatisticsMapper {
	List<MonthlyPurchaseDTO> selectMonthlyPurchaseList(
		@Param("userNo") int userNo,
		@Param("yearMonth") String yearMonth);

	List<IngredientPurchaseDTO> selectIngredientPurchaseList(
		@Param("userNo") int userNo);

	List<CategoryPurchaseDTO> selectCategoryPurchaseList(
		@Param("userNo") int userNo);

	List<DisposalHistoryDTO> selectDisposalHistoryList(
		@Param("userNo") int userNo,
		@Param("startDate") String startDate,
		@Param("endDate") String endDate
	);

	  List<MonthlyDisposalDTO> selectMonthlyDisposalList(
		@Param("userNo") int userNo);
}