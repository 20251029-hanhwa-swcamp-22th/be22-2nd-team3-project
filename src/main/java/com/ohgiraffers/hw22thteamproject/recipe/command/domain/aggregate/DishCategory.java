package com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dish_category")
public class DishCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dish_category_no", nullable = false)
	private Integer id;

	@Size(max = 100)
	@NotNull
	@Column(name = "dish_category_name", nullable = false, length = 100)
	private String dishCategoryName;

	@OneToMany
	@JoinColumn(name = "dish_category_no")
	private Set<Dish> dishes = new LinkedHashSet<>();

	@NotNull
	@ColumnDefault("current_timestamp()")
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@NotNull
	@ColumnDefault("current_timestamp()")
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

}