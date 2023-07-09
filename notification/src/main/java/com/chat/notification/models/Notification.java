package com.chat.notification.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long messageId;

	@Column(name = "sender_id", nullable = false, length = 50)
	private String senderId;

	@ManyToMany
	@JoinTable(name = "notification_recipient", joinColumns = @JoinColumn(name = "notification_id"),
			inverseJoinColumns = @JoinColumn(name = "recipient_username", referencedColumnName = "username"))
	private Set<Recipient> recipientUsernames;

	@Column(name = "chat", nullable = false)
	private String chat;

	@Column(name = "content", nullable = false, length = 1000)
	private String content;

	@Column(name = "sent_at")
	private String sentAt;

	public Notification() {
	}

	public Notification(String senderId, String chat, String content, String sentAt) {
		this.senderId = senderId;
		this.chat = chat;
		this.content = content;
		this.sentAt = sentAt;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public Set<Recipient> getRecipientUsernames() {
		return recipientUsernames;
	}

	public void setRecipientUsernames(Set<Recipient> recipientUsernames) {
		this.recipientUsernames = recipientUsernames;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}

	@Override
	public String toString() {
		return "Message{" + "messageId=" + messageId + ", senderId='" + senderId + '\'' + ", content='" + content + '\''
				+ ", sentAt=" + sentAt + '}';
	}

}
