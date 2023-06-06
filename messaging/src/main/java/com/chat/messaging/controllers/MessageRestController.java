package com.chat.messaging.controllers;

import com.chat.messaging.models.ChatRoom;
import com.chat.messaging.models.Message;
import com.chat.messaging.repository.ChatRoomRepository;
import com.chat.messaging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    @PostMapping("/{id}")
    public Message createMessage(@PathVariable("id") Long id, @RequestBody Message message){

        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);

        if(chatRoom.isEmpty()){
            throw new IllegalArgumentException("ChatRoom with roomId " + id + " does not exist.");
        }

        message.setChatRoom(chatRoom.get());
        return messageRepository.save(message);
    }
}
