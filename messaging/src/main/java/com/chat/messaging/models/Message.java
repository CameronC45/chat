package com.chat.messaging.models;

import com.chat.messaging.pojo.MessagePojo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

//
//@Entity
//@Table(name = "messages")
public class Message extends MessagePojo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    private ChatRoom chatRoom;

    public Message() {}

    public Message(String senderId, String content, ChatRoom chatRoom) {
        super(senderId, content);
        this.chatRoom = chatRoom;
    }

    public Message(MessagePojo messagePojo, ChatRoom chatRoom) {
        this(messagePojo.getSenderId(), messagePojo.getContent(), chatRoom);
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

    public ChatRoom getChatRoom(){
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
    }

}
