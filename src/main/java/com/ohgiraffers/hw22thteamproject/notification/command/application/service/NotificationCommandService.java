package com.ohgiraffers.hw22thteamproject.notification.command.application.service;

import com.ohgiraffers.hw22thteamproject.jwt.JwtTokenProvider;
import com.ohgiraffers.hw22thteamproject.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.hw22thteamproject.notification.command.domain.repository.NotificationDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class NotificationCommandService {

    private final NotificationDomainRepository notificationDomainRepository;

    @Transactional
    public void checkNotification(Long notificationNo) {
        Notification notice = this.notificationDomainRepository.getNotificationByNotificationNo(notificationNo);
        notice.setCheckTrue();
    }
}
