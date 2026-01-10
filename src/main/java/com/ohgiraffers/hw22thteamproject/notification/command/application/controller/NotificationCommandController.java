package com.ohgiraffers.hw22thteamproject.notification.command.application.controller;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.hw22thteamproject.notification.command.application.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationCommandController {
	// 1. [수정됨] 자기 자신(Controller)을 선언하면 에러 납니다! 지웠습니다.
	// 오직 Service만 필요합니다.
	private final NotificationService notificationService;

	@GetMapping ("/test")
	public String createTest(
		// 2. [수정됨] 입력받을 값들을 쉼표(,)로 구분해서 적어줍니다.
		// 서비스 쪽에서 인수를 3개(userNo, typeNo, content) 받기로 했으니 여기서도 3개를 받아야 합니다.
		@RequestParam Integer userNo,
		@RequestParam Integer typeNo,
		@RequestParam String content
	){
		// 3. [수정됨] 서비스는 괄호() 안이 아니라, 중괄호 {} 안에서 "명령"해야 합니다.
		notificationService.testNotification(userNo, typeNo, content);

		return "알림 생성 성공 DB확인해주세요";
	}
}
