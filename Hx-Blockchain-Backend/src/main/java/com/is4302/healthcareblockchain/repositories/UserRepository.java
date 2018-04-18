package com.is4302.healthcareblockchain.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is4302.healthcareblockchain.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}