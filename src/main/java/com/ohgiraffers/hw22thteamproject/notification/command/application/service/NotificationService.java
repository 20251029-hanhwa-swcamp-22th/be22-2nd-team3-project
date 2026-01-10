package com.ohgiraffers.hw22thteamproject.notification.command.application.service;

import org.springframework.stereotype.Service;

import com.ohgiraffers.hw22thteamproject.notification.command.domain.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;
}
