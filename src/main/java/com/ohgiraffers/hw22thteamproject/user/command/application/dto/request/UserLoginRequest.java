package com.ohgiraffers.hw22thteamproject.user.command.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginRequest {

    private String userId;
    private String password;

}
