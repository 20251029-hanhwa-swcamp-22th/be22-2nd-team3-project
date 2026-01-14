package com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;

public interface DishRepository {

	Dish save(Dish dish);

	Optional<Dish> findById(Integer id);

	void deleteById(Integer id);

	Optional<Dish> findByDishName(String dishName);

}
