package com.ohgiraffers.hw22thteamproject.user.query.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.user.query.dto.response.UserDetailResponse;
import com.ohgiraffers.hw22thteamproject.user.query.service.UserQueryService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserQueryController {

    private final UserQueryService userQueryService;

    @GetMapping("/users/{user_id}")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getUser(@PathVariable("user_id") String userId) {
        UserDetailResponse response = this.userQueryService.getUser(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/users/myinfo")
    public ResponseEntity<ApiResponse<UserDetailResponse>> getMyInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserDetailResponse response = this.userQueryService.getUser(userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
