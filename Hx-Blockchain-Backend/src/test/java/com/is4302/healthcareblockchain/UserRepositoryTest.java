package com.is4302.healthcareblockchain;

import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.is4302.healthcareblockchain.entities.User;
import com.is4302.healthcareblockchain.repositories.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
 
    @Test
    public void whenFindByUsername_thenReturnUser() {
        User found = userRepository.findByUsername("ADMIN1");      
        assertThat(found.getUsername())
          .isEqualTo("ADMIN1");
    }
    
    @Test
    public void whenFindByUnknownUsername_thenReturnNull() {
        User found = userRepository.findByUsername("unknownuser");      
        assertNull(found);
    }
 
}