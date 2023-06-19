package com.chat.notification.rabbitmq;
import com.chat.notification.models.Notification;
import com.chat.notification.repository.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MessageSubscriber {

    @Autowired
    private NotificationRepository repository;

    public void receiveMessage(String message) throws JsonProcessingException {
        //String strMessage = new String(message, StandardCharsets.UTF_8);
        System.out.println("Received raw: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        Notification receivedNotification = objectMapper.readValue(message, Notification.class);
        repository.save(receivedNotification);
    }
}
