package com.ohgiraffers.hw22thteamproject.notification.command.application.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.ohgiraffers.hw22thteamproject.notification.command.application.dto.request.CreateNotificationDTO;
import com.ohgiraffers.hw22thteamproject.notification.command.domain.aggregate.Notification;
import com.ohgiraffers.hw22thteamproject.notification.command.domain.repository.NotificationRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Service
@RequiredArgsConstructor
public class NotificationService { // <- 왜 NotificationService 중복이지???
	private final NotificationRepository notificationRepository;

	// @PostMapping("/test") 여기서 Post 는 말이안됨 Get써야함, Post는 Controller클래스에서 사용
	@Transactional
public void testNotification(Integer userNo, Integer typeNo, String content){
Notification newtestNotification = new Notification(userNo, typeNo, content);
		notificationRepository.save(newtestNotification);
	}
}
//Integer userNo,
//Integer notificationTypeNo,
//String notificationContent

