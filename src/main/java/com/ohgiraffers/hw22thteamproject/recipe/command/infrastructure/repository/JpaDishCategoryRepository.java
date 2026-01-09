package com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishCategoryRepository extends DishCategoryRepository, JpaRepository<DishCategory,Long> {

}
