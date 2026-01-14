package com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.DisposalHistory;

public interface DisposalDomainRepository {
    DisposalHistory save(DisposalHistory disposalHistory);
}
