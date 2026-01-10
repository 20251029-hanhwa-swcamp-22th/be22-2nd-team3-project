package com.ohgiraffers.hw22thteamproject.notification.notification.command.domain.aggregate;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer notificationNo;

//	@Column(name = "notification_no")
//	private Integer notificationNo;

	@Column(name = "user_no")
	private Integer userNo;

	@Column(name = "notification_type_no")
	private Integer notificationTypeNo;

	@Column(name = "notification_content")
	private String  notificationContent;

	@Column(name = "notification_is_checked")
	private Boolean notificationIsChecked; // 나는 처음에 Integer했는데 ai가 Boolean하라고함

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public void Notification(
		Integer userNo,
		Integer notificationTypeNo,
		String notificationContent
	){
this.userNo = userNo;
this.notificationTypeNo = notificationTypeNo;
this.notificationContent = notificationContent;
	}


}
