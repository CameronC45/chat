package com.chat.notification.pojo;

import java.time.LocalDateTime;

public class NotificationPojo {

    private Long notificationId;
    private String userId;
    private String message;
    private LocalDateTime timeStamp;

    public NotificationPojo() {}

    public NotificationPojo(String userId, String message) {
        this.userId = userId;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "NotificationPojo{" +
                "notificationId=" + notificationId +
                ", userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
