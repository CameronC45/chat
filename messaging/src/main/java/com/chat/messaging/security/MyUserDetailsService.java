package com.chat.messaging.security;

import com.chat.messaging.models.User;
import com.chat.messaging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(passwordEncoder.encode(user.get().getPassword()));
        userBuilder.roles("USER");

        return userBuilder.build();

    }
}
