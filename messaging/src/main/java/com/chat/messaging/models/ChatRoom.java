package com.chat.messaging.models;

import com.chat.messaging.pojo.ChatRoomPojo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_room")
public class ChatRoom extends ChatRoomPojo {

    public ChatRoom(){}

    public ChatRoom(String name){
        super(name);
    }

    public ChatRoom(ChatRoomPojo chatRoomPojo){
        this(chatRoomPojo.getName());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getRoomId() {
        return super.getRoomId();
    }

    @Column(name = "name", length = 50)
    @Override
    public String getName() {
        return super.getName();
    }

    @Column(name = "created_at")
    @Override
    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }
}
