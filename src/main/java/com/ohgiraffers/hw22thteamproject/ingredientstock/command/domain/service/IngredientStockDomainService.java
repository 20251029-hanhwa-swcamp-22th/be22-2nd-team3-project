package com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.service;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.dto.request.IngredientStockCreateRequest;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.IngredientStock;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.StockUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientStockDomainService {

    public void validateValue(IngredientStockCreateRequest ingredientStockCreateRequest) {
        // ? 검증 필요시 로직 추가
    }

    public List<IngredientStock> filterExpiredSoonStock(List<IngredientStock> ingredientStocks) {
        // return List<ingredientStock> with ingredientStockExpiredAt - now() <= 72 hours
        LocalDate now = LocalDate.now();
        return ingredientStocks.stream()
                .filter(stock -> {
                    long hoursUntilExpiry = ChronoUnit.HOURS.between(now.atStartOfDay(), stock.getIngredientStockExpiredAt().atStartOfDay());
                    return hoursUntilExpiry <= 72 && hoursUntilExpiry >= 0;
                })
                .collect(Collectors.toList());
    }

    public List<IngredientStock> filterLowStock(List<IngredientStock> ingredientStocks) {
        // 1. List<ingredientStock> with ingredientStockExpiredAt - now() > 72 hours & ingredientStockUnit = g & ingredientStockNowQuantity < 50
        // 2. List<ingredientStock> with ingredientStockExpiredAt - now() > 72 hours & ingredientStockUnit = ml & ingredientStockNowQuantity < 50
        // 3. List<ingredientStock> with ingredientStockExpiredAt - now() > 72 hours & ingredientStockUnit = ea & ingredientStockNowQuantity < 5
        // return 1,2,3 List
        LocalDate now = LocalDate.now();
        return ingredientStocks.stream()
                .filter(stock -> {
                    long hoursUntilExpiry = ChronoUnit.HOURS.between(now.atStartOfDay(), stock.getIngredientStockExpiredAt().atStartOfDay());

                    // Only include stocks that are NOT expiring soon (> 72 hours)
                    if (hoursUntilExpiry <= 72) {
                        return false;
                    }

                    // Check quantity thresholds based on unit
                    StockUnit unit = stock.getIngredientStockUnit();
                    long nowQuantity = stock.getIngredientStockNowQuantity();

                    if ((unit == StockUnit.g || unit == StockUnit.ml) && nowQuantity < 50) {
                        return true;
                    }

                    if (unit == StockUnit.ea && nowQuantity < 5) {
                        return true;
                    }

                    return false;
                })
                .collect(Collectors.toList());
    }
}
