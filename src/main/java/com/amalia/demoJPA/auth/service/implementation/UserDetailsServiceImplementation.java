package com.amalia.demoJPA.auth.service.implementation;

import com.amalia.demoJPA.auth.entity.UserAuth;
import com.amalia.demoJPA.users.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Attempting to load user by email: {}", email);
        var userData = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        log.info("User found: {}", userData.getEmail());
        return new UserAuth(userData);
    }
}
