package com.chat.messaging.controllers;

import com.chat.messaging.models.ChatRoom;
import com.chat.messaging.models.Message;
import com.chat.messaging.rabbitmq.MessagePublisher;
import com.chat.messaging.repository.ChatRoomRepository;
import com.chat.messaging.repository.MessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messaging")
public class MessageRestController {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	@Autowired
	private MessagePublisher messagePublisher;

	private static final Logger log = LoggerFactory.getLogger(MessageRestController.class);

	@GetMapping
	public List<Message> getAllMessages() {
		return messageRepository.findAll();
	}

	@PostMapping("/{id}")
	public ResponseEntity<Message> createMessage(@PathVariable("id") Long id, @RequestBody Message message) {

		Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);

		if (chatRoom.isEmpty()) {
			throw new IllegalArgumentException("ChatRoom with roomId " + id + " does not exist.");
		}

		message.setChatRoom(chatRoom.get());
		Message savedMessage = messageRepository.save(message);

		try {
			messagePublisher.sendMessage(savedMessage);
		}
		catch (JsonProcessingException e) {
			// log the exception, you might also want to alert your operations team
			// depending on how you've set up logging and alerts
			log.error("Failed to serialize message for RabbitMQ", e);
			// since this is an unexpected error, we'll return a 500 Internal Server Error
			// response
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(savedMessage);
	}

}
