package com.ohgiraffers.recipeservice.ingredientstock.command.domain.repository;

import com.ohgiraffers.recipeservice.ingredientstock.command.domain.aggregate.DisposalHistory;

public interface DisposalDomainRepository {
    DisposalHistory save(DisposalHistory disposalHistory);
}
