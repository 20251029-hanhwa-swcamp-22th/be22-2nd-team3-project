package com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository;

import java.util.Optional;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;

public interface DishCategoryRepository {
	Optional<DishCategory> findById(Integer id);
}
