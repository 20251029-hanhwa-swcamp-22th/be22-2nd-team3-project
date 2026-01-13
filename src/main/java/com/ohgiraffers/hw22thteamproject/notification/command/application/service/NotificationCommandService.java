package com.ohgiraffers.hw22thteamproject.notification.command.application.service;

import com.ohgiraffers.hw22thteamproject.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class NotificationCommandService {

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void setNotification(String refreshToken) {
        // 1. 로그인 시점의 JWT Token에서 user_no, issuedAt 가져오기
        this.jwtTokenProvider.validateToken(refreshToken);
        long userNo = Long.parseLong(this.jwtTokenProvider.getUserNoFromJWT(refreshToken));
        System.out.println("userNo = " + userNo);
        Date login = this.jwtTokenProvider.getIssuedAtDateFromJWT(refreshToken);
        System.out.println("login = " + login);

        // 2. user_no와 매칭되는 ingredient_stock 데이터 가져오기
        // 3. 유통기한 72시간 이하인 데이터 필터링
        // 4. 유통기한 72시간 이상, (g(50), ml(50), ea(50))인 데이터 필터링
        // 5. 필터링한 데이터를 notification 테이블에 저장
    }

}
