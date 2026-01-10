package com.ohgiraffers.hw22thteamproject.user.query.mapper;

import com.ohgiraffers.hw22thteamproject.user.query.dto.response.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /* 유저 조회 (유저 id) */
    UserDTO selectUserByUserId(String userId);

}
