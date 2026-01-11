package com.ohgiraffers.hw22thteamproject.user.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.RefreshToken;

public interface UserAuthRepository {

    RefreshToken save(RefreshToken tokenEntity);

    void deleteById(String userId);
}
