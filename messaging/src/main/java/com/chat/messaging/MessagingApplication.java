package com.chat.messaging;

import com.chat.messaging.models.Message;
import com.chat.messaging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingApplication implements CommandLineRunner {
	private final MessageRepository messageRepository;

	@Autowired
	public MessagingApplication(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testMessageRepository();
	}

	public void testMessageRepository(){
		messageRepository.save(new Message("11111111","8778878","hello"));
	}

}
