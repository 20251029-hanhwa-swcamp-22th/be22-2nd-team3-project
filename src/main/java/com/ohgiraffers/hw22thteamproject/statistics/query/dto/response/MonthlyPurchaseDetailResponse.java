package com.ohgiraffers.hw22thteamproject.statistics.query.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MonthlyPurchaseDetailResponse {
	private List<MonthlyPurchaseDTO> stock;
}