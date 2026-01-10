package com.ohgiraffers.hw22thteamproject.user.command.domain.repository;


import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUserId(String userId);
}
