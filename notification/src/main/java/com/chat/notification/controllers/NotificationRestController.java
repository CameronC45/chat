package com.chat.notification.controllers;

import com.chat.notification.models.Notification;
import com.chat.notification.repository.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notification")
public class NotificationRestController {

	@Autowired
	private NotificationRepository repository;

	@GetMapping
	public List<Notification> getAllNotifications() {
		return repository.findAll();
	}

	@GetMapping("/recipients/{username}")
	public List<Notification> getNotificationsByUsername(@PathVariable String username) {
		return repository.findByRecipientUsernames_Username(username);
	}

}
