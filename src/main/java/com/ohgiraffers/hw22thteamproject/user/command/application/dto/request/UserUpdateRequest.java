package com.ohgiraffers.hw22thteamproject.user.command.application.dto.request;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserUpdateRequest {

    private final String email;
    private final String phoneNum;

}
