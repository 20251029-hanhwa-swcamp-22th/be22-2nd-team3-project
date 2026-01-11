package com.ohgiraffers.hw22thteamproject.statistics.query.dto.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MonthlyPurchaseDTO {
	private int ingredientStockNo;
	private String ingredientStockName;
	private String ingredientStockCategory;
	private String ingredientStockUnit;
	private int ingredientStockTotalQuantity;
	private int ingredientStockTotalCost;
	private LocalDateTime ingredientStockBoughtAt;
	private LocalDate ingredientStockExpiredAt;
}