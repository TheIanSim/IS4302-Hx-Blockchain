package com.is4302.healthcareblockchain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.is4302.healthcareblockchain.controllers.MainController;
import com.is4302.healthcareblockchain.entities.Role;
import com.is4302.healthcareblockchain.entities.User;
import com.is4302.healthcareblockchain.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
    @Autowired
    private UserRepository userRepository;
	
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	logger.debug("Entering loadUserByUsername with value - " + username);
        User user = userRepository.findByUsername(username.toUpperCase());
        if ( user==null ){ return null; }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
       	logger.debug("Exiting loadUserByUsername");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}