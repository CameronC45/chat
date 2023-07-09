package com.chat.user.security;

import com.chat.user.models.User;
import com.chat.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);

		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}

		User retrievedUser = user.get();
		return org.springframework.security.core.userdetails.User.withUsername(retrievedUser.getUsername())
				.password(retrievedUser.getPassword()).roles("USER").build();
	}

}
