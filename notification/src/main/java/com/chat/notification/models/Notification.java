package com.chat.notification.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @Column(name = "sender_id", nullable = false, length = 50)
    private String senderId;
    @Column(name = "recipient_username", nullable = false, length = 50)
    private String recipientUsername;
    @Column(name = "content", nullable = false, length = 1000)
    private String content;
    @Column(name = "sent_at")
    private String sentAt;


    public Notification() {}

    public Notification(String senderId, String content, String sentAt) {
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }


    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderId='" + senderId + '\'' +
                ", content='" + content + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}
