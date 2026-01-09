package com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDishRepository extends DishRepository, JpaRepository<Dish,Long> {
}
