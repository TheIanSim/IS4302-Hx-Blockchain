package com.is4302.healthcareblockchain.services;

import java.util.Optional;

import com.is4302.healthcareblockchain.entities.User;

public interface UserService {

	User findByUsername(String username);
	
	boolean hasRole(User user, String role);

}
