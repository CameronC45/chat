package com.chat.messaging.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "participants")
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long participantId;

	@Column(name = "user_id", nullable = false, length = 50)
	private String userId;

	@Column(name = "joined_at")
	private LocalDateTime joinedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	@JsonBackReference
	private ChatRoom chatRoom;

	@PrePersist
	public void prePersist() {
		joinedAt = LocalDateTime.now();
	}

	public Participant() {
	}

	public Participant(String userId) {
		this.userId = userId;
		this.joinedAt = LocalDateTime.now();
	}

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
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

	public ChatRoom getChatRoom() {
		return chatRoom;
	}

	public void setChatRoom(ChatRoom chatRoom) {
		this.chatRoom = chatRoom;
	}

	@Override
	public String toString() {
		return "Participant{" + "participantId=" + participantId + ", userId='" + userId + '\'' + ", joinedAt="
				+ joinedAt + ", chatRoomId=" + (chatRoom != null ? chatRoom.getRoomId() : null) + '}';
	}

}
