package com.ohgiraffers.hw22thteamproject.recipe.query.mapper;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.ohgiraffers.hw22thteamproject.recipe.query.dto.response.DishCategoryDTO;

@MybatisTest // MyBatis 관련 설정만 로드
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 혹은 설정된 H2 사용
class DishCategoryMapperTest {

	@Autowired
	private DishCategoryMapper dishCategoryMapper;

	@Test
	@DisplayName("전체 카테고리 목록 조회 테스트")
	void selectAllDishCategories() {
		// When
		List<DishCategoryDTO> categories = dishCategoryMapper.selectAllDishCategories();

		// Then
		assertThat(categories).isNotNull();
		// 초기 데이터가 있다면 size 체크 가능
		Assertions.assertEquals(categories.size(), 8);
	}

	@Test
	@DisplayName("특정 ID로 카테고리 조회")
	void selectDishCategoryById() {
		// Given: ID가 1인 카테고리가 존재한다고 가정
		int targetId = 1;   // 한식이 저장되어있음

		// When
		Optional<DishCategoryDTO> result = dishCategoryMapper.selectDishCategoryById(targetId);

		// Then
		assertThat(result)
			.isPresent() // 값이 존재하는지 먼저 확인
			.hasValueSatisfying(category -> {
				// 내부 객체를 꺼내어 필드 검증
				assertThat(category.getDishCategoryName()).isEqualTo("한식");
			});
	}
}