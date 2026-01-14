package com.ohgiraffers.hw22thteamproject.ingredientstock.command.infrastructure.repository;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.DisposalHistory;
import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.repository.DisposalDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDisposalDomainRepository extends JpaRepository<DisposalHistory, Long>, DisposalDomainRepository {

}
