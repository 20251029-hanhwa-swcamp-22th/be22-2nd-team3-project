package com.ohgiraffers.hw22thteamproject.user.command.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class UserCreateRequest {

    private final String userId;
    private final String password;
    private final String nickname;
    private final String email;
    private final String phoneNum;
    private final Date birthdate;

}
