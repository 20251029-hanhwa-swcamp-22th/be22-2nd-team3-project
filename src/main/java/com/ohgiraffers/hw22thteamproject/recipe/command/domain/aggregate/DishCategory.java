package com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

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

  @NotNull
  @ColumnDefault("current_timestamp()")
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @NotNull
  @ColumnDefault("current_timestamp()")
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

}