package com.ohgiraffers.userservice.user.query.service;

import com.ohgiraffers.userservice.exception.BusinessException;
import com.ohgiraffers.userservice.exception.ErrorCode;
import com.ohgiraffers.userservice.jwt.JwtTokenProvider;
import com.ohgiraffers.userservice.user.command.domain.aggregate.User;
import com.ohgiraffers.userservice.user.command.infrastructure.repository.JpaUserRepository;
import com.ohgiraffers.userservice.user.query.dto.response.UserDTO;
import com.ohgiraffers.userservice.user.query.dto.response.UserDetailResponse;
import com.ohgiraffers.userservice.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final JpaUserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDetailResponse getUser(String userId) {
        // 조회 결과가 없을 경우 예외 발생, 있을 경우 ProductDTO 반환
        UserDTO user = Optional.ofNullable(this.userMapper.selectUserByUserId(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        // 빌더 패턴을 이용해서 ProductDetailResponse 객체 생성
        return UserDetailResponse.builder().user(user).build();

    }

    public UserDetailResponse getUserByToken(String refreshToken) {
        String userId = this.jwtTokenProvider.getUserIdFromJWT(refreshToken);
        UserDTO user = Optional.ofNullable(this.userMapper.selectUserByUserId(userId))
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return UserDetailResponse.builder().user(user).build();
    }

    @Transactional(readOnly = true)
    public UserDetailResponse getUserByUserNo(Long userNo) {
        User user = Optional.ofNullable(this.userRepository.findByUserNo(userNo))
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        UserDTO userDTO = UserDTO.builder()
                .userNo(user.getUserNo())
                .id(user.getUserId())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneNum(user.getPhoneNum())
                .birthdate(user.getBirthdate() != null ? user.getBirthdate().toString() : null)
                .registeredAt(user.getRegisteredAt())
                .isNoticeActive(user.getIsNoticeActive())
                .role(user.getRole())
                .build();

        return UserDetailResponse.builder().user(userDTO).build();
    }
}
