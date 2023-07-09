package com.chat.notification.models;

public class NotificationDTO {

	private Long messageId;

	private String senderId;

	private String chat;

	private String content;

	private String sentAt;

	private String[] recipientUsername;

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

	public String[] getRecipientUsername() {
		return recipientUsername;
	}

	public void setRecipientUsername(String[] recipientUsername) {
		this.recipientUsername = recipientUsername;
	}

}
