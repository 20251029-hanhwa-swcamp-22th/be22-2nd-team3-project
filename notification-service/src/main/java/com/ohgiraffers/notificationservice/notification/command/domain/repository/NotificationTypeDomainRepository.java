package com.ohgiraffers.notificationservice.notification.command.domain.repository;

import com.ohgiraffers.notificationservice.notification.command.domain.aggregate.NotificationType;

import java.util.Optional;

public interface NotificationTypeDomainRepository {

    Optional<NotificationType> findByNotificationTypeNo(Integer notificationTypeNo);
}