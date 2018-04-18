package com.is4302.healthcareblockchain.controllers;

import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
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
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private PersonService patientService;

	@Value("${composer.rest}")
	private String URL;

	@RequestMapping(value = "/updatePersonalInfo", method = RequestMethod.POST)
	public ResponseEntity<?> updatePatientInfo(HttpServletRequest rq,@RequestBody String jsonInput) throws JSONException {
		logger.debug("Entering /updatePersonalInfo");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		if (patientService.updatePersonalDetails(user,jsonInput) ){
			logger.debug("Exiting /updatePersonalInfo with no errors");
			return new ResponseEntity<String>(HttpStatus.OK);
		} 
		else{
			logger.debug("Exiting /updatePersonalInfo with errors");
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to update personal details").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/fulfilPre", method = RequestMethod.POST)
	public ResponseEntity<?> fulfilPre(HttpServletRequest rq,@RequestBody String jsonInput) throws JSONException {
		logger.debug("Entering /fulfilPre");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		if (!patientService.fulfilPre(user, jsonInput)){
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to fulfil prescription").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/mcAccess", method = RequestMethod.POST)
	public ResponseEntity<?> mcAccess(HttpServletRequest rq,@RequestBody String jsonInput) throws JSONException {
		logger.debug("Entering /mcAccess");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		if (!patientService.processMCAccess(user, jsonInput)){
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to fulfil prescription").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/infoAccess", method = RequestMethod.POST)
	public ResponseEntity<?> processInfoAccess(HttpServletRequest rq,@RequestBody String jsonInput) throws JSONException {
		logger.debug("Entering /infoAccess");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		if (!patientService.processInfoAccessToPractitioner(user, jsonInput)){
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to process info access").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
	
	@RequestMapping(value = "/preAccess", method = RequestMethod.POST)
	public ResponseEntity<?> processPreAccess(HttpServletRequest rq,@RequestBody String jsonInput) throws JSONException {
		logger.debug("Entering /preAccess");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		if (!patientService.processPreAccess(user, jsonInput)){
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to process pres access").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createMedInfo", method = RequestMethod.POST)
	public ResponseEntity<?> createMedInfo(HttpServletRequest rq,@RequestBody String jsonInput) throws JSONException {
		logger.debug("Entering /createMedInfo");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		if (!patientService.issueMedInfo(user, jsonInput)){
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to process createMedInfo").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createMC", method = RequestMethod.POST)
	public ResponseEntity<?> createMC(HttpServletRequest rq,@RequestBody String jsonInput) throws JSONException {
		logger.debug("Entering /createMC");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		if (!patientService.issueMC(user, jsonInput)){
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to process createMC").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/createPre", method = RequestMethod.POST)
	public ResponseEntity<?> createPre(HttpServletRequest rq,@RequestBody String jsonInput) throws JSONException {
		logger.debug("Entering /createPre");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		if (!patientService.issuePrescription(user, jsonInput)){
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to process createPRE").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getPatientInfo", method = RequestMethod.GET)
	public ResponseEntity<?> getPatientInfo(HttpServletRequest rq) throws JSONException {
		logger.debug("Entering /getPatientInfo");
		Principal principal = rq.getUserPrincipal();
		User user = userService.findByUsername(principal.getName());
		String patientInfo = patientService.getPersonalDetails(user);
		if ( patientInfo != null ){
			return new ResponseEntity<String>(patientInfo.toString()
					, HttpStatus.OK);
		} 
		else{
			return new ResponseEntity<String>(new JSONObject()
					.put("Message",  "Unable to retrieve personal details").toString()
					, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}