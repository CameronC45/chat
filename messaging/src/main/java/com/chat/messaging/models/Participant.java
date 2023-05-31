package com.chat.messaging.models;

import com.chat.messaging.pojo.ParticipantPojo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "participants")
public class Participant extends ParticipantPojo {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    private ChatRoom chatRoom;

    public Participant(String userId, ChatRoom chatRoom) {
        super(userId);
        this.chatRoom = chatRoom;
    }

    public Participant(ParticipantPojo participantPojo, ChatRoom chatRoom){
        this(participantPojo.getUserId(), chatRoom);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public String getParticipantId() {
        return super.getParticipantId();
    }

    @Column(name = "user_id", nullable = false, length = 50)
    @Override
    public String getUserId() {
        return super.getUserId();
    }

    @Column(name = "joined_at")
    @Override
    public LocalDateTime getJoinedAt() {
        return super.getJoinedAt();
    }

    public ChatRoom getChatRoom(){
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
    }
}
