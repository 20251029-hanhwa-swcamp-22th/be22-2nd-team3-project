package com.ohgiraffers.hw22thteamproject.user.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDetailResponse {

    private final UserDTO user;

}
