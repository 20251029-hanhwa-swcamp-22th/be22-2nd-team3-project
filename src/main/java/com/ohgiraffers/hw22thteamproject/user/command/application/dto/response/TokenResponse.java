package com.ohgiraffers.hw22thteamproject.user.command.application.dto.response;

import com.ohgiraffers.hw22thteamproject.jwt.JwtTokenProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@Builder
public class TokenResponse {

    private final String accessToken;
    private final String refreshToken;

}
