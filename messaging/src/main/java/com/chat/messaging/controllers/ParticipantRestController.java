package com.chat.messaging.controllers;

import com.chat.messaging.models.ChatRoom;
import com.chat.messaging.models.Participant;
import com.chat.messaging.repository.ChatRoomRepository;
import com.chat.messaging.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participant")
public class ParticipantRestController {

	@Autowired
	private ParticipantRepository participantRepository;

	@Autowired
	private ChatRoomRepository chatRoomRepository;

	@GetMapping
	public ResponseEntity<List<Participant>> getAllParticipants() {
		List<Participant> participants = participantRepository.findAll();
		return ResponseEntity.ok(participants);
	}

	@PostMapping("/{id}")
	public ResponseEntity<Participant> createParticipant(@PathVariable("id") Long id,
			@RequestBody Participant participant) {
		Optional<ChatRoom> chatRoom = chatRoomRepository.findById(id);

		if (chatRoom.isEmpty()) {
			throw new IllegalArgumentException("ChatRoom with roomId " + id + " does not exist.");
		}
		participant.setChatRoom(chatRoom.get());
		Participant savedParticipant = participantRepository.save(participant);
		return new ResponseEntity<>(savedParticipant, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
		Optional<Participant> participant = participantRepository.findById(id);
		return participant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
		if (participantRepository.existsById(id)) {
			participantRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
