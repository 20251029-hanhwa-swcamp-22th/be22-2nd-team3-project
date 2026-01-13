package com.ohgiraffers.hw22thteamproject.notification.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.notification.command.domain.aggregate.NotificationType;

import java.util.Optional;

public interface NotificationTypeDomainRepository {

    Optional<NotificationType> findByNotificationTypeNo(Integer notificationTypeNo);
}