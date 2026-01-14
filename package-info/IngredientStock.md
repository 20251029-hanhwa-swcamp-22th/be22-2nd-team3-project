# ingredientStock package 구현 기능
1. user_no 연관된 row 조회: GET /api/v1/ingredient-stocks
2. 로그인한 유저의 식재료 정보 등록: POST /api/v1/ingredient-stock
3. 로그인한 유저의 식재료 정보 수정: PATCH /api/v1/ingredient-stock
4. 로그인 유저의 폐기 이력 추가 : POST /api/v1/disposal



# Command
- ## application
  - IngredientStockCommandController
    - 식재료 관리 컨트롤러
  - DisposalCommandController
    - 폐기이력 관리 컨트롤러
- ## domain
- ## infrastructure

# Query
- ## controller
- ## dto
- ## mapper
- ## service