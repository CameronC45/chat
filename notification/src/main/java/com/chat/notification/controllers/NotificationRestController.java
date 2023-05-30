package com.chat.notification.controllers;

import com.chat.notification.models.Notification;
import com.chat.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationRestController {

    @Autowired
    private NotificationRepository repository;

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification){
        return  repository.save(notification);
    }
}
