package com.chat.messaging.controllers;

import com.chat.messaging.models.User;
import com.chat.messaging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserRestController {

    @Autowired
    private UserRepository repository;

    @PostMapping(consumes = {"application/json"})
    public User createUser(@RequestBody User user){
        return repository.save(user);
    }
}
