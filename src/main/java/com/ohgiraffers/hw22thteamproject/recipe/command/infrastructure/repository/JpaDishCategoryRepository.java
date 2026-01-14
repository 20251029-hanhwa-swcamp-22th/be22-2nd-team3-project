package com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishCategoryRepository;

public interface JpaDishCategoryRepository extends DishCategoryRepository, JpaRepository<DishCategory, Integer> {
}
