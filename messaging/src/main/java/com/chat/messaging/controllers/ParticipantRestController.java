package com.chat.messaging.controllers;

import com.chat.messaging.models.Participant;
import com.chat.messaging.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ParticipantRestController {

    @Autowired
    private ParticipantRepository participantRepository;

    @GetMapping
    public ResponseEntity<List<Participant>> getAllParticipants() {
        List<Participant> participants = participantRepository.findAll();
        return ResponseEntity.ok(participants);
    }

    @PostMapping
    public ResponseEntity<Participant> createParticipant(@RequestBody Participant participant){
        Participant savedParticipant = participantRepository.save(participant);
        return new ResponseEntity<>(savedParticipant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id){
        Optional<Participant> participant = participantRepository.findById(id);
        return participant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id){
        if(participantRepository.existsById(id)){
            participantRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
