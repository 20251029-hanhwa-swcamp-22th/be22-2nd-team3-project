package com.ohgiraffers.recipeservice.ingredientstock.query.dto.response;

import com.ohgiraffers.recipeservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class DisposalHistoryResponse {

    private final List<DisposalHistory> disposalHistories;

}
