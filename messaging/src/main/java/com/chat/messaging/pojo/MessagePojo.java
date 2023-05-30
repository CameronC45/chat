package com.chat.messaging.pojo;

import java.time.LocalDateTime;

public class MessagePojo {

    private Long messageId;
    private String senderId;
    private String roomId;
    private String content;
    private LocalDateTime sentAt;

    public MessagePojo() {}

    public MessagePojo(String senderId, String roomId, String content) {
        this.senderId = senderId;
        this.roomId = roomId;
        this.content = content;
        this.sentAt = LocalDateTime.now();
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

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public String toString() {
        return "MessagePojo{" +
                "messageId='" + messageId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", content='" + content + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}
