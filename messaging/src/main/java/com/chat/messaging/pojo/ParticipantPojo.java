package com.chat.messaging.pojo;

import java.time.LocalDateTime;

public class ParticipantPojo {

    private String participantId;
    private String roomId;
    private String userId;
    private LocalDateTime joinedAt;

    public ParticipantPojo(String roomId, String userId) {
        this.roomId = roomId;
        this.userId = userId;
        this.joinedAt = LocalDateTime.now();
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    @Override
    public String toString() {
        return "ParticipantPojo{" +
                "participantId='" + participantId + '\'' +
                ", roomId='" + roomId + '\'' +
                ", userId='" + userId + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }
}