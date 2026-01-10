package com.ohgiraffers.hw22thteamproject.notification.command.application.dto.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNotificationDTO {
	private Integer userNo;
	private Integer notificationTypeNo;
	private String notificationContent;
	private LocalDateTime date;


}
