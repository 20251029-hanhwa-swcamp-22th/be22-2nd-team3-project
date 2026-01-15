package com.ohgiraffers.recipeservice.notification.command.application.service;

import com.ohgiraffers.recipeservice.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.recipeservice.notification.command.domain.repository.NotificationDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
