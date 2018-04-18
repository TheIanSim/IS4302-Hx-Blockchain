package com.is4302.healthcareblockchain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.is4302.healthcareblockchain.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}