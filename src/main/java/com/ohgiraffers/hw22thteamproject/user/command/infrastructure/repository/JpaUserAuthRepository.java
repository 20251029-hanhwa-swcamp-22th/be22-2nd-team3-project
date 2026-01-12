package com.ohgiraffers.hw22thteamproject.user.command.infrastructure.repository;

import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.RefreshToken;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserAuthRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserAuthRepository extends JpaRepository<RefreshToken, String>, UserAuthRepository {

}
