package com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;

import java.util.Optional;

public interface DishRepository {

	Dish save(Dish dish);

	void deleteById(Integer id);

}
