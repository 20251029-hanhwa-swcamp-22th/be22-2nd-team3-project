package com.ohgiraffers.recipeservice.ingredientstock.query.mapper;

import com.ohgiraffers.recipeservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DisposalMapper {

    List<DisposalHistory> getAllDisposalHistoriesByUserNo(long userNo);
}
