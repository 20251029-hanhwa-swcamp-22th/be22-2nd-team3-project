package com.ohgiraffers.hw22thteamproject.notification.command.infrastructure.repository;

import com.ohgiraffers.hw22thteamproject.notification.command.domain.aggregate.NotificationType;
import com.ohgiraffers.hw22thteamproject.notification.command.domain.repository.NotificationTypeDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationTypeRepository extends JpaRepository<NotificationType, Integer>, NotificationTypeDomainRepository {
}