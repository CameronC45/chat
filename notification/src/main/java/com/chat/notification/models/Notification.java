package com.chat.notification.models;

import com.chat.notification.pojo.NotificationPojo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification extends NotificationPojo {

    @Column(name = "read", nullable = false)
    private Boolean read;

    public Notification() {}

    public Notification(String userId, String message) {
        super(userId, message);
        this.read = false;
    }

    public Notification(NotificationPojo notificationPojo){
        this(notificationPojo.getUserId(), notificationPojo.getMessage());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getNotificationId() {
        return super.getNotificationId();
    }

    @Column(name = "user_id", nullable = false, length = 50)
    @Override
    public String getUserId() {
        return super.getUserId();
    }

    @Column(name = "message", nullable = false, length = 200)
    @Override
    public String getMessage() {
        return super.getMessage();
    }


    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @Column(name = "time_stamp")
    @Override
    public LocalDateTime getTimeStamp() {
        return super.getTimeStamp();
    }
}
