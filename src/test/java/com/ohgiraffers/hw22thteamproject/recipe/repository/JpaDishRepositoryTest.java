package com.ohgiraffers.hw22thteamproject.recipe.repository;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.Dish;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.aggregate.DishCategory;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishCategoryRepository;
import com.ohgiraffers.hw22thteamproject.recipe.command.domain.repository.DishRepository;
import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JpaDishRepositoryTest {

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DishCategoryRepository dishCategoryRepository;

	@Test
	@DisplayName("Dish 저장 및 ID로 조회 테스트")
	void saveAndFindDishByIdTest() {
		// Given 1: User 불러오기
		User user = userRepository.findByUserId("gusgh075")
			.orElseThrow(() -> new IllegalArgumentException("테스트용 유저가 없습니다."));

		// Given 2: Category 불러오기
		DishCategory dishCategory = dishCategoryRepository.findById(1)
			.orElseThrow(() -> new IllegalArgumentException("테스트용 카테고리가 없습니다."));


		// Given 3: Dish 생성
		// Dish는 엔티티 내부에 @Builder.Default로 createdAt 등의 기본값이 설정되어 있어 생략 가능하지만,
		// userNo, dishCategoryNo 등 필수 연관관계는 반드시 포함해야 합니다.
		Dish dish = Dish.builder()
			.dishName("김치찌개")
			.dishImgFileRoute("/images/kimchi.jpg")
			.dishIsMarked(false)
			.userNo(user)
			.dishCategoryNo(dishCategory)
			.build();

		// When: 저장
		Dish savedDish = dishRepository.save(dish);

		// Then 1: 저장 확인
		assertThat(savedDish.getId()).isNotNull();

		// When: ID로 조회
		Optional<Dish> foundDishOptional = dishRepository.findById(savedDish.getId());

		// Then 2: 조회 결과 검증
		assertThat(foundDishOptional).isPresent();
		Dish foundDish = foundDishOptional.get();

		assertThat(foundDish.getDishName()).isEqualTo("김치찌개");
		assertThat(foundDish.getUserNo().getUserNo()).isEqualTo(user.getUserNo());
		assertThat(foundDish.getDishCategoryNo().getDishCategoryName()).isEqualTo("한식");
	}
}