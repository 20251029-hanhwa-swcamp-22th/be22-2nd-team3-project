package com.ohgiraffers.notificationservice.notification.query.controller;

import com.ohgiraffers.notificationservice.common.dto.ApiResponse;
import com.ohgiraffers.notificationservice.notification.query.dto.response.NotificationResponse;
import com.ohgiraffers.notificationservice.notification.query.service.NotificationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationQueryController {

    private final NotificationQueryService notificationQueryService;

    @GetMapping("/notification")
    public ResponseEntity<ApiResponse<Object>> getNotification(
            @AuthenticationPrincipal String userNo
    ){
        NotificationResponse response = this.notificationQueryService.getNotificationByUserNo(userNo);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
