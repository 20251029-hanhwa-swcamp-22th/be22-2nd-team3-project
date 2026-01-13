package com.ohgiraffers.hw22thteamproject.notification.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.notification.command.domain.aggregate.Notification;

import java.util.List;

public interface NotificationDomainRepository {
    <S extends Notification> List<S> saveAll(Iterable<S> entities);
}
