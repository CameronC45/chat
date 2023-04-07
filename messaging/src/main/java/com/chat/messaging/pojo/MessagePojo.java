package com.chat.messaging.pojo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessagePojo {

    private String message_id;
    private String sender_id;
    private String recipient_id;
    private String content;
    private LocalDateTime timeStamp;

    public MessagePojo(){}

    public MessagePojo(String message_id, String sender_id, String recipient_id, String content) {
        this.message_id = message_id;
        this.sender_id = sender_id;
        this.recipient_id = recipient_id;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }

    public String getMessage_id() {
        return message_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public String getContent() {
        return content;
    }

    public String getTimeStamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return timeStamp.format(formatter);
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
