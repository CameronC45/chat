package com.chat.notification.controllers;

import com.chat.notification.models.Notification;
import com.chat.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationRestController {

    @Autowired
    private NotificationRepository repository;

    @GetMapping
    public List<Notification> getNotifications() {
        return repository.findAll();
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification){
        return  repository.save(notification);
    }
}
