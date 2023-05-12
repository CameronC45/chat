package com.chat.messaging;

import com.chat.messaging.models.Message;
import com.chat.messaging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingApplication  {


	public static void main(String[] args) {
		SpringApplication.run(MessagingApplication.class, args);
	}

}
