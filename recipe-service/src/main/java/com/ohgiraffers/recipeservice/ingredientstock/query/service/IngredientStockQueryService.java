package com.ohgiraffers.recipeservice.ingredientstock.query.service;

import com.ohgiraffers.recipeservice.ingredientstock.command.domain.aggregate.IngredientStock;
import com.ohgiraffers.recipeservice.ingredientstock.query.dto.response.IngredientStockResponse;
import com.ohgiraffers.recipeservice.ingredientstock.query.mapper.IngredientStockMapper;
import com.ohgiraffers.recipeservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientStockQueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final IngredientStockMapper ingredientStockMapper;

    public IngredientStockResponse getIngredientStockList(String refreshToken) {
        // refreshToken에서 userNo 가져오기
        long userNo = Long.parseLong(this.jwtTokenProvider.getUserNoFromJWT(refreshToken));

        // ingredient_stock table에서 user_no와 매핑되는 컬럼 가져오기
        List<IngredientStock> ingredientStocks = this.ingredientStockMapper.getStocksByUserNo(userNo);

        return new IngredientStockResponse(ingredientStocks);
    }

}
