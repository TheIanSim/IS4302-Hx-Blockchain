/*package com.is4302.healthcareblockchain;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.is4302.healthcareblockchain.controllers.MainController;
import com.is4302.healthcareblockchain.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainRestControllerTest {
 
    private MockMvc mvc;
    
    @Autowired
    private WebApplicationContext context;
 
    @MockBean
    private UserService service;
 
    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    public void invalidUser()
      throws Exception {
    	
    	mvc.perform(formLogin().user("user").password("invalid"))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/login?error"))
		.andExpect(unauthenticated());
             
    }
    
    @Test
    public void givenLogout()
      throws Exception {
    	mvc
    	.perform(logout()).andExpect(unauthenticated());

    }
    
    @Test
    public void validUser()
      throws Exception {
         
    	mvc
    	.perform(formLogin().user("admin1").password("pp")).andExpect(status().isFound())
    	.andExpect(authenticated());
    	
    	//mvc.perform(formLogin()).andExpect(status().isFound())
		//.andExpect(authenticated().withUsername("user"));
        
    }
    
    
    
    @Test
    @WithMockUser(username = "admintest", password = "passwordtest" , authorities = { "ADMIN", "ROLE_ADMIN" })
    public void givenUser_whenGreeting_thenReturnString()
      throws Exception {
         
        Employee alex = new Employee("alex");
     
     service.findByUsername(username)
        given(service.getAllEmployees()).willReturn(allEmployees);
     
        mvc.perform(get("/greeting2")
          .contentType(MediaType.ALL))
          .andExpect(status().isOk())
          .andExpect(content().string(containsString("got admin")));
             
    }

}*/