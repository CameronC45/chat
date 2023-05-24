package com.chat.messaging.models;

import com.chat.messaging.pojo.MessagePojo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends MessagePojo {

    public Message() {}

    public Message(String senderId, String recipientId, String content) {
        super(senderId, recipientId, content);
    }

    public Message(MessagePojo messagePojo) {
        this(messagePojo.getSenderId(), messagePojo.getRecipientId(), messagePojo.getContent());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getMessageId() {
        return super.getMessageId();
    }

    @Column(name = "sender_id", nullable = false, length = 50)
    @Override
    public String getSenderId() {
        return super.getSenderId();
    }

    @Column(name = "recipient_id", nullable = false, length = 50)
    @Override
    public String getRecipientId() {
        return super.getRecipientId();
    }

    @Column(name = "content", nullable = false, length = 1000)
    @Override
    public String getContent() {
        return super.getContent();
    }

    @Column(name = "time_stamp")
    @Override
    public LocalDateTime getTimeStamp() {
        return super.getTimeStamp();
    }

}
