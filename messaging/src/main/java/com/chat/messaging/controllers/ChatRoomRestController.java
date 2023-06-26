package com.chat.messaging.controllers;

import com.chat.messaging.models.ChatRoom;
import com.chat.messaging.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat")
public class ChatRoomRestController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return ResponseEntity.ok(chatRooms);
    }

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom){
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        return new ResponseEntity<>(savedChatRoom, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable Long id){
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);
        return chatRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoom(@PathVariable Long id){
        if(chatRoomRepository.existsById(id)){
            chatRoomRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}