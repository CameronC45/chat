package com.chat.messaging.models;

import com.chat.messaging.pojo.MessagePojo;
import jakarta.annotation.Resource;
import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message extends MessagePojo {

    public Message(){}

    public Message(String message_id, String sender_id, String recipient_id, String content){
        super(message_id, sender_id, recipient_id, content);
    }

    public Message(MessagePojo messagePojo){
        this(messagePojo.getMessage_id(), messagePojo.getSender_id(),
                messagePojo.getRecipient_id(), messagePojo.getContent());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public String getMessage_id() {
        return super.getMessage_id();
    }

    @Column(name = "sender_id", nullable = false, length = 50)
    @Override
    public String getSender_id() {
        return super.getSender_id();
    }

    @Column(name = "recipient_id", nullable = false, length = 50)
    @Override
    public String getRecipient_id() {
        return super.getRecipient_id();
    }

    @Column(name = "content", nullable = false, length = 1000)
    @Override
    public String getContent() {
        return super.getContent();
    }

    @Column(name = "time_stamp")
    @Override
    public String getTimeStamp() {
        return super.getTimeStamp();
    }
}
