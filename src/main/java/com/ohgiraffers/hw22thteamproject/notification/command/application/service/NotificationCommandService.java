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

    }

}
