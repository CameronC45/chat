package com.chat.user.controllers;

import com.chat.user.models.User;
import com.chat.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {

		if (repository.existsByEmail(user.getEmail()) || repository.existsByUsername(user.getUsername())) {
			return new ResponseEntity<>("A user with the given email or username already exists.", HttpStatus.CONFLICT);
		}
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		User savedUser = repository.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/{email}")
	public ResponseEntity<User> getUser(@PathVariable String email) {
		Optional<User> user = repository.findById(email);
		return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public List<User> getAllUsers() {
		return repository.findAll();
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<Void> deleteUser(@PathVariable String email) {
		Optional<User> user = repository.findById(email);

		if (user.isPresent()) {
			repository.deleteById(email);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}