package com.ohgiraffers.notificationservice.notification.query.mapper;

import com.ohgiraffers.notificationservice.notification.query.dto.response.NotificationDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {

    List<NotificationDTO> getNotificationsByUserNo(long userNo);
}
