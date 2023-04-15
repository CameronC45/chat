package com.chat.messaging.models;

import com.chat.messaging.pojo.MessagePojo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends MessagePojo {

    public Message() {}

    public Message(String sender_id, String recipient_id, String content) {
        super(sender_id, recipient_id, content);
    }

    public Message(MessagePojo messagePojo) {
        this(messagePojo.getSender_id(), messagePojo.getRecipient_id(), messagePojo.getContent());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getMessage_id() {
        return super.getMessage_id();
    }

    @Override
    public void setMessage_id(Long message_id) {
        super.setMessage_id(message_id);
    }

    @Column(name = "sender_id", nullable = false, length = 50)
    @Override
    public String getSender_id() {
        return super.getSender_id();
    }

    @Override
    public void setSender_id(String sender_id) {
        super.setSender_id(sender_id);
    }

    @Column(name = "recipient_id", nullable = false, length = 50)
    @Override
    public String getRecipient_id() {
        return super.getRecipient_id();
    }

    @Override
    public void setRecipient_id(String recipient_id) {
        super.setRecipient_id(recipient_id);
    }

    @Column(name = "content", nullable = false, length = 1000)
    @Override
    public String getContent() {
        return super.getContent();
    }

    @Override
    public void setContent(String content) {
        super.setContent(content);
    }

    @Column(name = "time_stamp")
    @Override
    public LocalDateTime getTimeStamp() {
        return super.getTimeStamp();
    }

    @Override
    public void setTimeStamp(LocalDateTime timeStamp){
        super.setTimeStamp(timeStamp);
    }
}
