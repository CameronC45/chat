package com.chat.user.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;

@Component
public class JwtUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return JWT.create().withIssuer("chat-service").withSubject("user-identifier").withClaim("username", subject)
				.withIssuedAt(new Date()).withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000))
				.sign(Algorithm.HMAC256(secret));
	}

	public boolean validateToken(String token) {
		try {
			JWT.require(Algorithm.HMAC256(secret)).withIssuer("chat-service").build().verify(token);
			return true;
		}
		catch (Exception e) {
			LOGGER.error("Failed to validate token: {}", token, e);
			return false;
		}
	}

}
