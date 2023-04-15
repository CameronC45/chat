package com.chat.messaging.pojo;

import java.time.LocalDateTime;

public class MessagePojo {

    private Long message_id;
    private String sender_id;
    private String recipient_id;
    private String content;
    private LocalDateTime timeStamp;

    public MessagePojo() {}

    public MessagePojo(String sender_id, String recipient_id, String content) {
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }

    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "MessagePojo{" +
                "message_id='" + message_id + '\'' +
                ", sender_id='" + sender_id + '\'' +
                ", recipient_id='" + recipient_id + '\'' +
                ", content='" + content + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
