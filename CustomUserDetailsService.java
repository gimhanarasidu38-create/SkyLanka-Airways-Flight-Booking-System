package com.example.srilankaairways.config;

import com.example.srilankaairways.entity.User;
import com.example.srilankaairways.repository.UserRepository;
import java.util.Locale;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        String role = user.getRole() == null || user.getRole().isBlank()
                ? "CUSTOMER"
                : user.getRole().trim().toUpperCase(Locale.ROOT);

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(role.startsWith("ROLE_") ? role.substring("ROLE_".length()) : role)
                .build();
    }
}
