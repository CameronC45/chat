package com.chat.messaging.pojo;

import java.time.LocalDateTime;

public class ChatRoomPojo {

    private Long roomId;
    private String name;
    private LocalDateTime createdAt;

    public ChatRoomPojo() {}

    public ChatRoomPojo(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ChatRoomPojo{" +
                "roomId='" + roomId + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
