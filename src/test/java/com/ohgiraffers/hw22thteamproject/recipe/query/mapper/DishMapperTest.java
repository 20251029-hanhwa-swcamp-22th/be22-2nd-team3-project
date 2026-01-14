package com.ohgiraffers.hw22thteamproject.recipe.query.mapper;

import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DishMapperTest {

    @Autowired
    private DishMapper dishMapper;

    @Test
    @DisplayName("요리 번호로 상세 조회 시 Optional 처리 및 필드 검증")
    void selectDishByIdTest() {
        // Given: 테스트용 요리 번호 (DB에 1번 데이터가 있다고 가정)
        int targetDishNo = 1;

        // When
        Optional<DishDTO> result = dishMapper.selectDishById(targetDishNo);

        // Then
        assertThat(result)
            .isPresent() // 1. 값이 존재하는지 확인
            .hasValueSatisfying(dish -> { // 2. 존재한다면 내부 값을 꺼내 검증
                assertThat(dish.getDishNo()).isEqualTo(targetDishNo);
                assertThat(dish.getDishName()).isEqualTo("김치볶음밥"); // 요리 이름이 비어있지 않은지 확인
                assertThat(dish.getDishCategoryNo()).isEqualTo(1);
            });
    }

    @Test
    @DisplayName("사용자 번호로 요리 목록 조회 테스트")
    void selectDishesByUserTest() {
        // Given
        int userNo = 1;

        // When
        List<DishDTO> dishes = dishMapper.selectDishesByUser(userNo);

        // Then
        assertThat(dishes)
            .isNotNull()
            .allSatisfy(dish -> { // 리스트 내의 모든 요소가 특정 조건을 만족하는지 확인
                assertThat(dish).isNotNull();
                assertThat(dish.getUserNo()).isEqualTo(userNo);
                assertThat(dish.getDishName()).isNotEmpty();
                assertThat(dish.getDishCategoryNo()).isNotNull();
                // 모든 결과 데이터가 해당 사용자의 것인지 혹은 필요한 필드가 있는지 확인
            });
    }

    @Test
    @DisplayName("존재하지 않는 요리 번호 조회 시 empty 반환 확인")
    void selectDishByIdNotFoundTest() {
        // Given
        int invalidNo = -1;

        // When
        Optional<DishDTO> result = dishMapper.selectDishById(invalidNo);

        // Then
        assertThat(result).isEmpty();
    }
}