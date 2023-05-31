package com.chat.messaging.pojo;

//import com.chat.messaging.models.ChatRoom;

import java.time.LocalDateTime;

public class MessagePojo {

    private Long messageId;
    private String senderId;
    private String content;
    private LocalDateTime sentAt;


    public MessagePojo() {}

    public MessagePojo(String senderId, String content) {
        this.senderId = senderId;
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
                ", content='" + content + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}
