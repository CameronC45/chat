package com.chat.notification.rabbitmq;

import com.chat.notification.models.Notification;
import com.chat.notification.models.NotificationDTO;
import com.chat.notification.models.Recipient;
import com.chat.notification.repository.NotificationRepository;
import com.chat.notification.repository.RecipientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MessageSubscriber {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private RecipientRepository recipientRepository;

    public void receiveMessage(String message) throws JsonProcessingException {
        System.out.println("Received raw: " + message);
        ObjectMapper objectMapper = new ObjectMapper();
        NotificationDTO receivedNotification = objectMapper.readValue(message, NotificationDTO.class);

        Notification notification = new Notification();
        notification.setChat(receivedNotification.getChat());
        notification.setContent(receivedNotification.getContent());
        notification.setSenderId(receivedNotification.getSenderId());
        notification.setSentAt(receivedNotification.getSentAt());

        List<String> recipientUsernames = List.of(receivedNotification.getRecipientUsername());
        Set<Recipient> recipients = new HashSet<>();
        for (String username : recipientUsernames) {
            Optional<Recipient> recipient = recipientRepository.findById(username);
            if (recipient.isEmpty()) {
                Recipient newRecipient = new Recipient(username);
                recipientRepository.save(newRecipient);
                recipients.add(newRecipient);
            } else {
                recipients.add(recipient.get());
            }
        }
        notification.setRecipientUsernames(recipients);

        notificationRepository.save(notification);
    }
}