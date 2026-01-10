package com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recipe")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recipe_no", nullable = false)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "dish_no", nullable = false)
	private Dish dishNo;

	@Size(max = 1000)
	@NotNull
	@Column(name = "recipe_ingredient", nullable = false, length = 1000)
	private String recipeIngredient;

	@Size(max = 2000)
	@NotNull
	@Column(name = "recipe_cookery", nullable = false, length = 2000)
	private String recipeCookery;

	@NotNull
	@ColumnDefault("current_timestamp()")
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@NotNull
	@ColumnDefault("current_timestamp()")
	@Column(name = "updated_at", nullable = false)
	private Instant updatedAt;

}