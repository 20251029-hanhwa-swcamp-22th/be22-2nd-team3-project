package com.ohgiraffers.hw22thteamproject.statistics.query.mapper;

import java.util.List;

import com.ohgiraffers.hw22thteamproject.statistics.query.dto.request.DisposalCostRequest;
import com.ohgiraffers.hw22thteamproject.statistics.query.dto.response.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StatisticsMapper {
	List<MonthlyPurchaseDTO> selectMonthlyPurchaseList(
		@Param("userNo") int userNo,
		@Param("yearMonth") String yearMonth
	);

	List<IngredientPurchaseDTO> selectIngredientPurchaseList(
		@Param("userNo") int userNo
	);

	List<CategoryPurchaseDTO> selectCategoryPurchaseList(
		@Param("userNo") int userNo
	);

	List<DisposalCostDTO> selectDisposalCostList(
		@Param("userNo") int userNo,
		DisposalCostRequest disposalCostRequest
	);

	List<MonthlyDisposalDTO> selectMonthlyDisposalList(
		@Param("userNo") int userNo
	);
}