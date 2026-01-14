-- USER
INSERT INTO `user` (`user_no`, `user_id`, `user_pwd`, `user_nickname`, `user_email`,
                    `user_phonenum`, `user_birthdate`, `user_is_notice_active`)
VALUES (1, 'user0001', 'pwd12345678', '김철수', 'chulsoo@example.com', '01012345678', '1990-01-01', 'YYYYY'),
       (2, 'member_park', 'pwd23456789', '박영희', 'younghee@test.com', '01023456789', '1992-05-15', 'YYNNY'),
       (3, 'lee_dev99', 'pwd34567890', '이민수', 'minsoo_lee@web.com', '01034567890', '1988-11-20', 'YYYYN'),
       (4, 'choi_happy', 'pwd45678901', '최지우', 'jiwoo_choi@mail.com', '01045678901', '1995-03-10', 'NNNNN'),
       (5, 'jung_chef', 'pwd56789012', '정쉐프', 'chef_jung@food.com', '01056789012', '1985-07-30', 'YYYYY'),
       (6, 'kang_star', 'pwd67890123', '강하늘', 'sky_kang@example.com', '01067890123', '1993-12-25', 'YNYNY'),
       (7, 'yoon_runner', 'pwd78901234', '윤달리기', 'runner_yoon@test.com', '01078901234', '1991-08-14', 'NNYNN'),
       (8, 'lim_study', 'pwd89012345', '임공부', 'study_lim@web.com', '01089012345', '1998-02-05', 'YYYYY'),
       (9, 'han_winter', 'pwd90123456', '한겨울', 'winter_han@mail.com', '01090123456', '1994-01-20', 'YNNNY'),
       (10, 'shin_sunny', 'pwd01234567', '신햇살', 'sunny_shin@example.com', '01001234567', '1989-06-12', 'YYYYY');

-- INGREDIENT
INSERT INTO `ingredient` (`ingredient_no`, `ingredient_name`)
VALUES (1, '당근'),
       (2, '양파'),
       (3, '대파'),
       (4, '마늘'),
       (5, '감자'),
       (6, '닭가슴살'),
       (7, '돼지고기 목살'),
       (8, '소고기 등심'),
       (9, '고등어'),
       (10, '우유');

-- DISH_CATEGORY
INSERT INTO `dish_category` (`dish_category_no`, `dish_category_name`)
VALUES (1, '한식'),
       (2, '일식'),
       (3, '중식'),
       (4, '양식'),
       (5, '분식'),
       (6, '샐러드/다이어트'),
       (7, '디저트'),
       (8, '베이킹'),
       (9, '안주'),
       (10, '기타/창작');

-- NOTIFICATION_TYPE
INSERT INTO `notification_type` (`notification_type_no`, `notification_type_name`)
VALUES (1, '유통기한 임박'),
       (2, '재고 소진 임박'),
       (3, '레시피 추천'),
       (4, '영양 부족'),
       (5, '영양 과다'),
       (6, '유통기한 만료'),
       (7, '회원정보 변경'),
       (8, '시스템 공지'),
       (9, '식단 분석 완료'),
       (10, '이벤트 알림');

-- DISH
INSERT INTO `dish` (`dish_no`, `user_no`, `dish_category_no`, `dish_name`,
                    `dish_img_file_route`, `dish_is_marked`)
VALUES (1, 1, 1, '매콤 제육볶음', '/images/dishes/jeyuk.jpg', TRUE),
       (2, 2, 2, '연어 초밥', '/images/dishes/salmon_sushi.jpg', FALSE),
       (3, 3, 3, '해물 짬뽕', '/images/dishes/jjamppong.jpg', TRUE),
       (4, 4, 4, '까르보나라 파스타', '/images/dishes/pasta.jpg', FALSE),
       (5, 5, 5, '국물 떡볶이', '/images/dishes/tteokbokki.jpg', TRUE),
       (6, 6, 6, '닭가슴살 아보카도 샐러드', '/images/dishes/salad.jpg', FALSE),
       (7, 7, 7, '딸기 생크림 케이크', '/images/dishes/cake.jpg', FALSE),
       (8, 8, 1, '소고기 미역국', '/images/dishes/miyeokguk.jpg', TRUE),
       (9, 9, 9, '바지락 술찜', '/images/dishes/clam_stew.jpg', FALSE),
       (10, 10, 4, '치즈 햄버거', '/images/dishes/burger.jpg', TRUE);

-- RECIPE
INSERT INTO `recipe` (`recipe_no`, `recipe_ingredient`, `recipe_cookery`)
VALUES (1, '{"돼지고기": "300g", "양파": "1/2개", "고추장": "2T"}', '1. 고기를 양념에 재운다. 2. 팬에 야채와 함께 볶는다.'),
       (2, '{"연어": "200g", "단초물": "3T", "쌀": "1공기"}', '1. 밥에 단초물을 섞는다. 2. 연어를 슬라이스하여 밥 위에 올린다.'),
       (3, '{"오징어": "1마리", "면": "1인분", "고춧가루": "3T"}', '1. 해산물을 볶아 불맛을 낸다. 2. 육수를 붓고 끓인 뒤 면을 넣는다.'),
       (4, '{"스파게티면": "100g", "베이컨": "3줄", "달걀노른자": "2개"}', '1. 면을 삶는다. 2. 팬에 베이컨을 볶다 면과 노른자 소스를 섞는다.'),
       (5, '{"떡": "200g", "고추장": "2T", "어묵": "2장"}', '1. 물에 양념을 푼다. 2. 떡과 어묵을 넣고 국물이 졸을 때까지 끓인다.'),
       (6, '{"닭가슴살": "1개", "아보카도": "1/2개", "드레싱": "2T"}', '1. 닭가슴살을 굽는다. 2. 야채와 아보카도를 썰어 드레싱을 곁들인다.'),
       (7, '{"밀가루": "200g", "생크림": "300ml", "딸기": "10알"}', '1. 시트를 굽는다. 2. 생크림과 딸기를 층층이 쌓아 장식한다.'),
       (8, '{"소고기": "100g", "미역": "20g", "국간장": "1T"}', '1. 소고기와 미역을 참기름에 볶는다. 2. 물을 붓고 오래 끓인다.'),
       (9, '{"바지락": "300g", "화이트와인": "50ml", "마늘": "5알"}', '1. 마늘기름을 낸다. 2. 바지락과 와인을 넣고 뚜껑을 덮어 익힌다.'),
       (10, '{"패티": "1장", "번": "2개", "치즈": "1장"}', '1. 패티를 굽는다. 2. 번 사이에 패티, 치즈, 야채를 순서대로 쌓는다.');

-- INGREDIENT_STOCK
INSERT INTO `ingredient_stock` (`ingredient_stock_no`, `user_no`, `ingredient_stock_name`,
                                `ingredient_stock_expired_at`,
                                `ingredient_stock_total_quantity`, `ingredient_stock_total_cost`,
                                `ingredient_stock_now_quantity`, `ingredient_stock_category`,
                                `ingredient_stock_unit`, `ingredient_stock_bought_at`)
VALUES (1, 1, '유기농 당근', '2026-01-15', 500, 3000, 450, 'produce', 'g', '2026-01-01 10:00:00'),
       (2, 2, '냉동 닭가슴살', '2026-01-10', 10, 5000, 3, 'livestock', 'ea', '2026-01-05 14:00:00'),
       (3, 3, '자반 고등어', '2026-01-12', 1000, 15000, 800, 'seafood', 'g', '2026-01-08 09:30:00'),
       (4, 4, '햇양파 1망', '2026-05-20', 200, 1200, 200, 'produce', 'g', '2026-01-02 11:20:00'),
       (5, 5, '참치캔(대)', '2026-01-30', 5, 10000, 5, 'processed', 'ea', '2026-01-07 16:45:00'),
       (6, 6, '무항생제 달걀', '2026-02-15', 30, 9000, 15, 'livestock', 'ea', '2026-01-01 13:00:00'),
       (7, 7, '저지방 우유', '2026-01-05', 1000, 2500, 0, 'produce', 'ml', '2025-12-28 10:00:00'),
       (8, 8, '칵테일 새우', '2026-03-01', 500, 8000, 250, 'seafood', 'g', '2026-01-04 18:10:00'),
       (9, 9, '흙대파', '2026-04-10', 2000, 4000, 1500, 'produce', 'g', '2026-01-06 08:00:00'),
       (10, 10, '한우 등심', '2026-01-20', 10, 20000, 8, 'livestock', 'ea', '2026-01-05 20:30:00');

-- DISPOSAL_HISTORIES
INSERT INTO `disposal_histories` (`disposal_no`, `ingredient_stock_no`, `user_no`,
                                  `disposal_quantity`, `disposal_cost`, `disposal_reason`, `disposal_at`)
VALUES (1, 1, 1, 50, 300, '유통기한 임박으로 인한 일부 폐기', '2026-01-08 15:00:00'),
       (2, 2, 2, 7, 3500, '부패 및 변질', '2026-01-07 11:20:00'),
       (3, 3, 3, 200, 3000, '냄새 및 신선도 저하', '2026-01-09 09:10:00'),
       (4, 6, 6, 15, 4500, '보관 실수로 인한 파손', '2026-01-04 14:30:00'),
       (5, 7, 7, 1000, 2500, '유통기한 경과', '2026-01-06 00:05:00'),
       (6, 8, 8, 250, 4000, '장기 미사용', '2026-01-05 18:00:00'),
       (7, 10, 10, 2, 4000, '일부 변색', '2026-01-08 20:15:00'),
       (8, 5, 5, 1, 2000, '캔 파손', '2026-01-08 10:00:00'),
       (9, 9, 9, 500, 1000, '짓무름 발생', '2026-01-07 13:40:00'),
       (10, 4, 4, 10, 60, '단순 변심 폐기', '2026-01-05 16:20:00');

-- NOTIFICATION
INSERT INTO `notification` (`notification_no`, `user_no`, `notification_type_no`,
                            `notification_content`, `notification_is_checked`)
VALUES (1, 1, 1, '유기농 당근의 유통기한이 3일 남았습니다.', FALSE),
       (2, 2, 2, '냉동 닭가슴살 재고가 설정값 미만입니다.', TRUE),
       (3, 3, 6, '자반 고등어의 유통기한이 만료되었습니다.', FALSE),
       (4, 5, 3, '보유 재료로 만들 수 있는 제육볶음 레시피 추천!', TRUE),
       (5, 1, 4, '이번 주 비타민 A 섭취량이 권장량보다 부족합니다.', FALSE),
       (6, 6, 1, '무항생제 달걀의 유통기한이 1일 남았습니다.', FALSE),
       (7, 7, 6, '저지방 우유가 유통기한 만료로 자동 처리되었습니다.', TRUE),
       (8, 8, 2, '칵테일 새우 재고가 거의 다 떨어졌습니다.', FALSE),
       (9, 10, 5, '오늘 나트륨 섭취량이 권장량을 초과했습니다.', TRUE),
       (10, 4, 8, '서비스 점검 안내: 내일 새벽 2시부터 4시까지', FALSE);