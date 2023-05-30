package com.chat.messaging.models;

import com.chat.messaging.pojo.MessagePojo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message extends MessagePojo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private ChatRoom chatRoom;

    public Message() {}

    public Message(String senderId, String recipientId, String content) {
        super(senderId, recipientId, content);
    }

    public Message(MessagePojo messagePojo) {
        this(messagePojo.getSenderId(), messagePojo.getRoomId(), messagePojo.getContent());
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

    @Column(name = "room_id", nullable = false, length = 50)
    @Override
    public String getRoomId() {
        return super.getRoomId();
    }

    @Column(name = "content", nullable = false, length = 1000)
    @Override
    public String getContent() {
        return super.getContent();
    }

    @Column(name = "sent_at")
    @Override
    public LocalDateTime getSentAt() {
        return super.getSentAt();
    }

}
