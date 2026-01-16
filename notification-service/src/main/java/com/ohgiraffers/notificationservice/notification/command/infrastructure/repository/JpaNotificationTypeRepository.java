package com.ohgiraffers.notificationservice.notification.command.infrastructure.repository;

import com.ohgiraffers.notificationservice.notification.command.domain.aggregate.NotificationType;
import com.ohgiraffers.notificationservice.notification.command.domain.repository.NotificationTypeDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationTypeRepository extends JpaRepository<NotificationType, Integer>, NotificationTypeDomainRepository {
}