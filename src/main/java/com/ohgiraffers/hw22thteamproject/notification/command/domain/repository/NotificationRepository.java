package com.ohgiraffers.hw22thteamproject.notification.command.domain.repository;

import com.ohgiraffers.hw22thteamproject.notification.command.domain.aggregate.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

	// ⚠️ 여기 안에는 아무것도 적지 마세요! 텅 비워두세요!
	// JpaRepository가 save 기능을 이미 가지고 있습니다.

}