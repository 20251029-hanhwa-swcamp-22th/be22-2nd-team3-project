package com.ohgiraffers.hw22thteamproject.notification.command.infrastructure.repository;

import com.ohgiraffers.hw22thteamproject.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.hw22thteamproject.notification.command.domain.repository.NotificationDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository extends JpaRepository<Notification, Long>, NotificationDomainRepository {

}
