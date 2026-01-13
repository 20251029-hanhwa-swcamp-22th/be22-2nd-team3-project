package com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.service;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.application.dto.request.IngredientStockCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IngredientStockDomainService {

    public void validateValue(IngredientStockCreateRequest ingredientStockCreateRequest) {
        // ? 검증 필요시 로직 추가
    }

}
