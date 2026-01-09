package com.ohgiraffers.hw22thteamproject.recipe.repository;

import com.ohgiraffers.hw22thteamproject.recipe.command.infrastructure.repository.JpaRecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB(MariaDB) 연결 설정 유지
class RecipeRepositoryTest {

  @Autowired
  private JpaRecipeRepository jpaRecipeRepository;

  @Test
  @DisplayName("DB 연결 및 레포지토리 빈 주입 테스트")
  void testConnection() {
    // Given & When & Then
    // 레포지토리가 정상적으로 주입되었다면 DB 연결 설정에 문제가 없음을 의미합니다.
    Assertions.assertNotNull(jpaRecipeRepository);
  }

  @Test
  @DisplayName("데이터베이스 조회 테스트")
  void testCount() {
    // 실제 DB에 연결되어 조회가 가능한지 확인합니다.
    // 데이터가 없더라도 에러 없이 0 이상의 값이 반환되면 연결 성공입니다.
    long count = jpaRecipeRepository.count();
    Assertions.assertTrue(count >= 0);
  }
}