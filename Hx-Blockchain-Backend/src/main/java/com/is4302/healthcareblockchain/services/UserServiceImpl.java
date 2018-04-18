package com.is4302.healthcareblockchain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.is4302.healthcareblockchain.entities.Role;
import com.is4302.healthcareblockchain.entities.User;
import com.is4302.healthcareblockchain.repositories.RoleRepository;
import com.is4302.healthcareblockchain.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;



    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public boolean hasRole(User user, String role){
    	if (user == null) {
    		return false;
    	}
    	for ( Role r: user.getRoles() ){
    		if ( r.getName().equalsIgnoreCase(role) ){
    			return true;
    		}
    	}
    	return false;
    	
    }
}