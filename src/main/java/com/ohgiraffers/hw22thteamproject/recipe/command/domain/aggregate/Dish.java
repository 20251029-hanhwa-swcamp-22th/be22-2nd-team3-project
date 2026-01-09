package com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate;

import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
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
@Table(name = "dish")
public class Dish {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "dish_no", nullable = false)
  private Integer id;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_no", nullable = false)
  private User userNo;

  @Size(max = 20)
  @NotNull
  @Column(name = "dish_name", nullable = false, length = 20)
  private String dishName;

  @Size(max = 300)
  @NotNull
  @Column(name = "dish_img_file_route", nullable = false, length = 300)
  private String dishImgFileRoute;

  @NotNull
  @ColumnDefault("0")
  @Column(name = "dish_is_marked", nullable = false)
  private Boolean dishIsMarked = false;

  @NotNull
  @ColumnDefault("current_timestamp()")
  @Column(name = "created_at", nullable = false)
  private Instant createdAt;

  @NotNull
  @ColumnDefault("current_timestamp()")
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

}