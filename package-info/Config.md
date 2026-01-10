### ModelMapperConfig.java

- MatchingStrategies.STRICT
  - STRICT 모드: 소스 필드와 대상 필드의 이름이 정확히 일치할 때 매핑
- org.modelmapper.config.Configuration.AccessLevel.PRIVATE
  - AccessLevel.PRIVATE: Getter/Setter 없이 Private field에 접근 가능
- setFieldMatchingEnabled(true)
  - 메서드 기준이 아닌 필드 이름 자체를 기준으로 매핑을 시도

### SecurityConfig.java
- @EnableWebSecurity
- @EnableMethodSecurity