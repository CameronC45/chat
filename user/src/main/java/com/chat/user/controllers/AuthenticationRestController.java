package com.chat.user.controllers;

import com.chat.user.models.User;
import com.chat.user.repository.UserRepository;
import com.chat.user.security.JwtUtil;
import com.chat.user.security.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final Logger log = LoggerFactory.getLogger(AuthenticationRestController.class);

	@Autowired
	private JwtUtil jwt;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) {
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			boolean passwordMatches = passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
			if (passwordMatches) {
				authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			}
			else {
				return ResponseEntity.badRequest().body("Invalid username or password");
			}
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
		final String jwtToken = jwt.generateToken(userDetails);
		return ResponseEntity.ok(jwtToken);
	}

	@PostMapping("/validateToken")
	public ResponseEntity<Boolean> validateToken(@RequestBody Map<String, String> tokenPayload) {
		String token = tokenPayload.get("token");

		if (token == null) {
			return ResponseEntity.badRequest().build();
		}
		log.info("Validating JWT...");
		boolean valid = jwt.validateToken(token);
		return ResponseEntity.ok(valid);
	}

}
