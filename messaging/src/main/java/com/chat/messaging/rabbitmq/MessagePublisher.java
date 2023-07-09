package com.chat.messaging.rabbitmq;

import com.chat.messaging.models.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessagePublisher {

	private final AmqpTemplate amqpTemplate;

	private final ObjectMapper objectMapper;

	public MessagePublisher(AmqpTemplate amqpTemplate, ObjectMapper objectMapper) {
		this.amqpTemplate = amqpTemplate;
		this.objectMapper = objectMapper;
	}

	public void sendMessage(Message message) throws JsonProcessingException {
		String jsonMessage = objectMapper.writeValueAsString(message);
		System.out.println("Sending: " + jsonMessage);
		amqpTemplate.convertAndSend("messageExchange", "messageRoutingKey", jsonMessage);
	}

}
