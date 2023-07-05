package com.chat.messaging.controllers;

import com.chat.messaging.models.ChatRoom;
import com.chat.messaging.models.Participant;
import com.chat.messaging.models.UserIdentifier;
import com.chat.messaging.repository.ChatRoomRepository;
import com.chat.messaging.repository.ParticipantRepository;
import jakarta.servlet.http.Part;
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

    @Autowired
    private ParticipantRepository participantRepository;

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return ResponseEntity.ok(chatRooms);
    }

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom){
        List<Participant> participants = chatRoom.getParticipants();
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoom);
        for(Participant participant : participants) {
            participant.setChatRoom(savedChatRoom);
            participantRepository.save(participant);
        }
        return new ResponseEntity<>(savedChatRoom, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable Long id){
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);
        return chatRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user")
    public ResponseEntity<List<ChatRoom>> getChatRoomsByParticipantId(@RequestBody UserIdentifier user) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByUserId(user.getUserId());
        return ResponseEntity.ok(chatRooms);
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
