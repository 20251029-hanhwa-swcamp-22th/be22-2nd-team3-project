package com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate;

import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rcd_recipe")
public class RecommendRecipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "recommend_recipe", nullable = false)
  private Integer id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_no", nullable = false)
  private User userNo;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "dish_category_no", nullable = false)
  private DishCategory dishCategoryNo;

  @Size(max = 20)
  @NotNull
  @Column(name = "rcd_recipe_dish_name", nullable = false, length = 20)
  private String rcdRecipeDishName;

  @Size(max = 1000)
  @NotNull
  @Column(name = "rcd_recipe_ingredients", nullable = false, length = 1000)
  private String rcdRecipeIngredients;

  @Size(max = 1000)
  @NotNull
  @Column(name = "rcd_recipe_substitutions", nullable = false, length = 1000)
  private String rcdRecipeSubstitutions;

  @Size(max = 2000)
  @NotNull
  @Column(name = "rcd_recipe_cookery", nullable = false, length = 2000)
  private String rcdRecipeCookery;

  @Size(max = 2000)
  @NotNull
  @Column(name = "rcd_recipe_tips", nullable = false, length = 2000)
  private String rcdRecipeTips;

}