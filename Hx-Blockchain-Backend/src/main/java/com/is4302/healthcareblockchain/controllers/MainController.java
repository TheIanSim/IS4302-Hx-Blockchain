package com.is4302.healthcareblockchain.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.is4302.healthcareblockchain.entities.Role;
import com.is4302.healthcareblockchain.entities.User;
import com.is4302.healthcareblockchain.services.PersonService;
import com.is4302.healthcareblockchain.services.UserService;

@RestController
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PersonService personService;
	@Autowired
	private HttpHeaders JSONhttpHeaders;

	@Value("${composer.rest}")
	private String URL;

	@RequestMapping(value = "/postLogin", method = RequestMethod.GET)
	public String postLogin(HttpServletRequest rq) throws JSONException {
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		String s = "NONE";
		for ( Role rle : user.getRoles()){
			s = rle.getName();
		}
		if ( s.equalsIgnoreCase("patient")){
			return new JSONObject()
					.put("personalDetails",  personService.getPersonalDetails(user))
					.put("medicalInfo",  personService.getMedicalInfo(user))
					.put("medicalCerts",  personService.getMedicalCerts(user))
					.put("prescriptions",  personService.getPrescriptions(user))
					.put("patientInfo",  personService.getPatientInfo(user))
					.put("role",  s).toString();
		}
		else if ( s.equalsIgnoreCase("doctor") || s.equalsIgnoreCase("nurse")){
			return new JSONObject()
					.put("personalDetails",  personService.getPersonalDetails(user))
					.put("medicalInfo",  personService.getMedicalInfo(user))
					.put("prescriptions",  personService.getPrescriptions(user))
					.put("permissions",  personService.getDoctor(user))
					.put("role",  s).toString();
		}
		else if ( s.equalsIgnoreCase("PHARMACY")){
			return new JSONObject()
					.put("personalDetails",  personService.getPharmacy(user))
					.put("prescriptions",  personService.getPrescriptions(user))
					.put("role",  s).toString();		
		}
		return new JSONObject()
				.put("personalDetails",  personService.getPersonalDetails(user))
				.put("medicalCerts",  personService.getMedicalCerts(user))
				.put("role",  s).toString();
	}

}