package com.chat.messaging.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @Column(name = "sender_id", nullable = false, length = 50)
    private String senderId;
    @CollectionTable(name = "recipient_usernames", joinColumns = @JoinColumn(name = "message_id"))
    @Column(name = "recipient_username")
    private List<String> recipientUsername;

    @Column(name = "chat", nullable = false)
    private String chat;
    @Column(name = "content", nullable = false, length = 1000)
    private String content;
    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private ChatRoom chatRoom;

    @PrePersist
    public void prePersist() {
        sentAt = LocalDateTime.now();
    }

    public Message() {}

    public Message(String senderId, List<String> recipientUsername, String chat,String content) {
        this.senderId = senderId;
        this.recipientUsername = recipientUsername;
        this.chat = chat;
        this.content = content;
        this.sentAt = LocalDateTime.now();
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

    public List<String> getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(List<String> recipientUsername) {
        this.recipientUsername = recipientUsername;
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

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public ChatRoom getChatRoom(){
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom){
        this.chatRoom = chatRoom;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderId='" + senderId + '\'' +
                ", recipientUsername=" + recipientUsername +
                ", content='" + content + '\'' +
                ", sentAt=" + sentAt +
                ", chatRoomId=" + (chatRoom != null ? chatRoom.getRoomId() : null) +
                '}';
    }
}
