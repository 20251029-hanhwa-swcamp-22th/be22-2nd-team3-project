package com.ohgiraffers.hw22thteamproject.notification.command.application.controller;

import com.ohgiraffers.hw22thteamproject.common.dto.ApiResponse;
import com.ohgiraffers.hw22thteamproject.notification.command.application.service.NotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class NotificationCommandController {

    private final NotificationCommandService notificationCommandService;

    @PostMapping("notification")
    public ResponseEntity<ApiResponse<Void>> saveNotification(
            @CookieValue(name = "refreshToken") String refreshToken
            ) {
        this.notificationCommandService.setNotification(refreshToken);
        return null;
    }

}
