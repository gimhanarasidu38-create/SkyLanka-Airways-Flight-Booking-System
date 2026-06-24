package com.example.srilankaairways.controller;

import com.example.srilankaairways.entity.User;
import com.example.srilankaairways.repository.UserRepository;
import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public AuthController(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()
                || user.getPassword() == null || user.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        String role = user.getRole() == null || user.getRole().isBlank()
                ? "CUSTOMER"
                : user.getRole().trim().toUpperCase(Locale.ROOT);

        user.setPassword(
                encoder.encode(
                        user.getPassword()));
        user.setRole(role);

        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(user));
    }
}
