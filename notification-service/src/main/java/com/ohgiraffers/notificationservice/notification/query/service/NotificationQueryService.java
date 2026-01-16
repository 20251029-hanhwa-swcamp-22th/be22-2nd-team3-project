package com.ohgiraffers.notificationservice.notification.query.service;

import com.ohgiraffers.notificationservice.jwt.JwtTokenProvider;
import com.ohgiraffers.notificationservice.notification.query.dto.response.NotificationDTO;
import com.ohgiraffers.notificationservice.notification.query.dto.response.NotificationResponse;
import com.ohgiraffers.notificationservice.notification.query.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {

    private final NotificationMapper notificationMapper;

    public NotificationResponse getNotificationByUserNo(String providedUserNo) {
        long userNo = Long.parseLong(providedUserNo);

        // user_no가 일치하는 notification 전부 불러오기
        List<NotificationDTO> notifications = this.notificationMapper.getNotificationsByUserNo(userNo);

        return NotificationResponse.builder().notifications(notifications).build();
    }
}
