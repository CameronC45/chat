package com.chat.messaging.controllers;

import com.chat.messaging.models.Message;
import com.chat.messaging.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messaging")
public class MessageRestController {

    @Autowired
    private MessageRepository repository;

    @GetMapping(produces = {"application/json"})
    public List<Message> getAllMessages(){
        return repository.findAll();
    }

    @PostMapping(consumes = {"application/json"})
    public Message createUser(@RequestBody Message message){
        return repository.save(message);
    }
}
