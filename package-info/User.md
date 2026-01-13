# User Package 구현 기능
1. 회원가입: POST /api/v1/users
2. 로그인: POST /api/v1/user/login
3. 로그아웃: POST /api/v1/user/logout
4. 회원정보조회(user_id): GET /api/v1/user/{userId}
5. 내정보조회: GET /api/v1/user/me
6. 회원정보수정(email, phoneNum): PATCH /api/v1/users
7. 회원정보수정(password): PATCH /api/v1/users/password
8. 회원탈퇴: DELETE /api/v1/users

# Command
- ## Application
- ## Domain
- ## Infrastructure

# Query
- ## Controller
  - ### UserQueryController
    - user 테이블 조회(SELECT)만 수행하는 Controller class 
- ## DTO
  - ### UserDTO
    - user 상세 조회를 위한 DTO class
- ## Mapper
  - resources/mappers/user/**.xml 매핑되는 class
- ## Service
  - UserQueryController 에 들어온 요청을 처리하는 Service class
