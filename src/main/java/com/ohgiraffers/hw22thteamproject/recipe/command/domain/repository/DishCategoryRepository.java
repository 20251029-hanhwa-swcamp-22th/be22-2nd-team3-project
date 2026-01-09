package com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;

import java.util.Optional;

public interface DishCategoryRepository {

    DishCategory save(DishCategory dishCategory);

    Optional<DishCategory> findById(Integer id);

    void deleteById(Integer id);

}
