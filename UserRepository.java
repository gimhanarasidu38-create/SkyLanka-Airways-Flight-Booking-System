package com.example.srilankaairways.repository;

import com.example.srilankaairways.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}