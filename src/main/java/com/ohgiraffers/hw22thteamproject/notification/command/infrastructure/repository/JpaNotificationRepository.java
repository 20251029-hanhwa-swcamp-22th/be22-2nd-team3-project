package com.ohgiraffers.hw22thteamproject.notification.command.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ohgiraffers.hw22thteamproject.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.hw22thteamproject.notification.command.domain.repository.NotificationRepository;

public interface JpaNotificationRepository extends JpaRepository <Notification, Integer>, NotificationRepository {// 프록시가 자동으로 의존성 DI주입함

}
