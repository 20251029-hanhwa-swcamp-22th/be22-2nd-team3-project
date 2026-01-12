package com.ohgiraffers.hw22thteamproject.user.command.application.service;

import com.ohgiraffers.hw22thteamproject.jwt.JwtTokenProvider;
import com.ohgiraffers.hw22thteamproject.user.command.application.dto.request.UserCreateRequest;
import com.ohgiraffers.hw22thteamproject.user.command.application.dto.request.UserLoginRequest;
import com.ohgiraffers.hw22thteamproject.user.command.application.dto.response.TokenResponse;
import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.RefreshToken;
import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserAuthRepository;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserRepository;
import com.ohgiraffers.hw22thteamproject.user.command.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDomainService userDomainService;
    private final JwtTokenProvider jwtTokenProvider;


    /* 신규 회원 가입 */
    @Transactional
    public void registUser(UserCreateRequest userCreateRequest) {
        /* 중복되는 데이터가 입력되었는지 검증 */
        this.userDomainService.validateValue(userCreateRequest);

        /* Request(DTO) to User Entity */
        User user = this.modelMapper.map(userCreateRequest, User.class);

        /* 비밀번호 암호화 */
        user.setEncodedPassword(this.passwordEncoder.encode(userCreateRequest.getPassword()));
        /* 저장 */
        this.userRepository.save(user);
    }

    /* 로그인 */
    public TokenResponse login(UserLoginRequest loginRequest) {
        // 1. userId(user_id)로 조회 -> userId(user_id), password(암호화) 조회됨
        User user = this.userRepository.findByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다"));

        // 2. 비밀번호 매칭 확인
        if(!this.passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다");
        }

        // 3. 비밀번호가 일치 -> 로그인 성공 -> 토큰 생성 -> 발급
        String accessToken = this.jwtTokenProvider.createToken(user.getUserId(), user.getRole().name());
        String refreshToken = this.jwtTokenProvider.createRefreshToken(user.getUserId(), user.getRole().name());

        // 4. refresh token DB에 저장(보안 및 토큰 재발급 검증용)
        RefreshToken tokenEntity = RefreshToken.builder()
                .userId(user.getUserId())
                .token(refreshToken)
                .expiryDate(new Date(System.currentTimeMillis() + jwtTokenProvider.getRefreshExpiration())).build();

        this.userAuthRepository.save(tokenEntity);

        return TokenResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }


    public void logout(String refreshToken) {
        // refreshToken 검증 절차
        this.jwtTokenProvider.validateToken(refreshToken);

        String userId = this.jwtTokenProvider.getUserIdFromJWT(refreshToken);

        this.userAuthRepository.deleteById(userId);
    }
}
