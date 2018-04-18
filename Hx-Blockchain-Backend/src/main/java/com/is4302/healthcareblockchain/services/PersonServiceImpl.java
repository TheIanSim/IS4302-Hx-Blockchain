package com.is4302.healthcareblockchain.services;

import java.util.Random;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.is4302.healthcareblockchain.entities.User;

@Service
public class PersonServiceImpl implements PersonService{

	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HttpHeaders JSONhttpHeaders;

	@Value("${composer.rest}")
	private String URL;

	@Override
	public String getPersonalDetails(User user) throws JSONException {
		logger.debug("Entering getPersonalDetails() calling url:" + URL+user.getPort()+"/api/org.acme.model.Patient");
		ResponseEntity<String> composerResponse = restTemplate.getForEntity(URL+user.getPort()+"/api/org.acme.model.PersonalDetails", String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("getPersonalDetails() returning: " + composerResponse.getBody());
			return composerResponse.getBody();
		}
		logger.debug("Exiting getPersonalDetails() with null value with error: " + composerResponse.getBody());
		return null;
	}

	@Override
	public String getPharmacy(User user) throws JSONException {
		logger.debug("Entering getPharmacy() calling url:" + URL+user.getPort()+"/api/org.acme.model.Pharmacy");
		ResponseEntity<String> composerResponse = restTemplate.getForEntity(URL+user.getPort()+"/api/org.acme.model.Pharmacy", String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("getPharmacy() returning: " + composerResponse.getBody());
			return composerResponse.getBody();
		}
		logger.debug("Exiting getPharmacy() with null value with error: " + composerResponse.getBody());
		return null;
	}
	
	@Override
	public String getDoctor(User user) throws JSONException {
		logger.debug("Entering getDoctor() calling url:" + URL+user.getPort()+"/api/org.acme.model.Practitioner");
		ResponseEntity<String> composerResponse = restTemplate.getForEntity(URL+user.getPort()+"/api/org.acme.model.Practitioner", String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("getDoctor() returning: " + composerResponse.getBody());
			return composerResponse.getBody();
		}
		logger.debug("Exiting getDoctor() with null value with error: " + composerResponse.getBody());
		return null;
	}
	
	@Override
	public String getPatient(User user) throws JSONException {
		logger.debug("Entering getDoctor() calling url:" + URL+user.getPort()+"/api/org.acme.model.Patientr");
		ResponseEntity<String> composerResponse = restTemplate.getForEntity(URL+user.getPort()+"/api/org.acme.model.Patient", String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("getDoctor() returning: " + composerResponse.getBody());
			return composerResponse.getBody();
		}
		logger.debug("Exiting getDoctor() with null value with error: " + composerResponse.getBody());
		return null;
	}

	@Override
	public String getMedicalCerts(User user) throws JSONException {
		logger.debug("Entering getMedicalCerts() calling url:" + URL+user.getPort()+"/org.acme.model.MedicalCert");
		ResponseEntity<String> composerResponse = restTemplate.getForEntity(URL+user.getPort()+"/api/org.acme.model.MedicalCert", String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("getMedicalCerts() returning: " + composerResponse.getBody());
			return composerResponse.getBody();
		}
		logger.debug("Exiting getMedicalCerts() with null value with error: " + composerResponse.getBody());
		return null;
	}

	@Override
	public String getPrescriptions(User user) throws JSONException {
		logger.debug("Entering getPrescriptions() calling url:" + URL+user.getPort()+"/org.acme.model.Prescription");
		ResponseEntity<String> composerResponse = restTemplate.getForEntity(URL+user.getPort()+"/api/org.acme.model.Prescription", String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("getPrescriptions() returning: " + composerResponse.getBody());
			return composerResponse.getBody();
		}
		logger.debug("Exiting getPrescriptions() with null value with error: " + composerResponse.getBody());
		return null;
	}

	@Override
	public String getMedicalInfo(User user) throws JSONException {
		logger.debug("Entering getMedicalInfo() calling url:" + URL+user.getPort()+"/org.acme.model.MedicalInfo");
		ResponseEntity<String> composerResponse = restTemplate.getForEntity(URL+user.getPort()+"/api/org.acme.model.MedicalInfo", String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("getMedicalInfo() returning: " + composerResponse.getBody());
			return composerResponse.getBody();
		}
		logger.debug("Exiting getMedicalInfo() with null value with error: " + composerResponse.getBody());
		return null;
	}

	@Override
	public String getPatientInfo(User user) throws JSONException {
		logger.debug("Entering getPatientInfo() calling url:" + URL+user.getPort()+"/org.acme.model.Patient");
		ResponseEntity<String> composerResponse = restTemplate.getForEntity(URL+user.getPort()+"/api/org.acme.model.Patient", String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("getPatientInfo() returning: " + composerResponse.getBody());
			return composerResponse.getBody();
		}
		logger.debug("Exiting getPatientInfo() with null value with error: " + composerResponse.getBody());
		return null;
	}

	@Override
	public boolean updatePersonalDetails(User user, String jsonInput) throws JSONException {
		logger.debug("Entering updatePersonalDetails() POSTing url:" + URL+user.getPort()+"/api/org.acme.model.UpdatePersonalDetails");
		JSONObject jsonObj = new JSONObject(jsonInput);
		jsonObj.put("$class", "org.acme.model.UpdatePersonalDetails");
		String tempPdId = (String) jsonObj.get("detailsId");
		jsonObj.remove("detailsId");
		jsonObj.put("pd", tempPdId);
		HttpEntity<String> entity = new HttpEntity<String>(jsonObj.toString(), JSONhttpHeaders);
		logger.debug("Entering updatePersonalDetails() POSTED:"+ jsonObj.toString());

		ResponseEntity<String> composerResponse = restTemplate
				.exchange(URL+user.getPort()+"/api/org.acme.model.UpdatePersonalDetails", HttpMethod.POST, entity, String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("Exting updatePersonalDetails() successfully");
			return true;
		}
		logger.debug("Exting updatePersonalDetails() with error: "+composerResponse.getBody());
		return false;

	}

	@Override
	public boolean processPreAccess(User user,String jsonInput) throws JSONException {
		logger.debug("Entering processPreAccess()");
		JSONObject input = new JSONObject(jsonInput);
		JSONObject toSend = new JSONObject();
		String role =  (String) input.get("role");
		String patient = (String)  input.get("patient");
		String person =  (String) input.get("accessor");
		String action =  (String) input.get("a");



		if ( role.equalsIgnoreCase("pharmacy")){
			toSend.put("$class", "org.acme.model.ProcessPreAccessToPharm");
			toSend.put("patient", patient);
			toSend.put("ph", person);
			toSend.put("a", action);
			logger.debug("Entering processPreAcTOSEND"+toSend.toString());

			HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);
			ResponseEntity<String> composerResponse = restTemplate
					.exchange(URL+user.getPort()+"/api/org.acme.model.ProcessPreAccessToPharm", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() == HttpStatus.OK) {
				logger.debug("processPreAccessToPharm() returning: " + composerResponse.getBody());
				return true;
			}
			logger.debug("Exiting processPreAccessToPharm() with null value with error: " + composerResponse.getBody());
			return false;
		}
		else{
			toSend.put("$class", "org.acme.model.ProcessPreAccessToPractitioner");
			toSend.put("patient", patient);
			toSend.put("practitioner", person);
			toSend.put("a", action);
			logger.debug("Entering processPreAcTOSEND"+toSend.toString());
			HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);

			ResponseEntity<String> composerResponse = restTemplate
					.exchange(URL+user.getPort()+"/api/org.acme.model.ProcessPreAccessToPractitioner", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() == HttpStatus.OK) {
				logger.debug("processPreAccesToPractitioner() returning: " + composerResponse.getBody());
				return true;
			}
			logger.debug("Exiting processPreAccesToPractitioner() with null value with error: " + composerResponse.getBody());
			return false;
		}
	}

	@Override
	public boolean processMCAccess(User user,String jsonInput) throws JSONException {
		logger.debug("Entering processMCAccess()");
		JSONObject input = new JSONObject(jsonInput);
		JSONObject toSend = new JSONObject();
		String role =  (String) input.get("role");
		String patient = (String)  input.get("patient");
		String person =  (String) input.get("accessor");
		String action =  (String) input.get("a");

		if ( role.equalsIgnoreCase("doctor")){
			toSend.put("$class", "org.acme.model.ProcessMCAccessToPractitioner");
			toSend.put("patient", patient);
			toSend.put("practitioner", person);
			toSend.put("a", action);
			HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);
			logger.debug("processMCAccess() tosenddoc returning: "+ toSend.toString());
			ResponseEntity<String> composerResponse = restTemplate
					.exchange(URL+user.getPort()+"/api/org.acme.model.ProcessMCAccessToPractitioner", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() == HttpStatus.OK) {
				logger.debug("processMCAccess() returning: " + composerResponse.getBody());
				return true;
			}
			logger.debug("Exiting processMCAccess() with null value with error: " + composerResponse.getBody());
			return false;
		}
		else{ //Employer
			toSend.put("$class", "org.acme.model.ProcessMCAccessToEmployer");
			toSend.put("patient", patient);
			toSend.put("em", person);
			toSend.put("a", action);
			HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);
			logger.debug("processMCAccess() tosend returning: "+ toSend.toString());

			ResponseEntity<String> composerResponse = restTemplate
					.exchange(URL+user.getPort()+"/api/org.acme.model.ProcessMCAccessToEmployer", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() == HttpStatus.OK) {
				logger.debug("processMCAccess() returning: " + composerResponse.getBody());
				return true;
			}
			logger.debug("Exiting processMC() with null value with error: " + composerResponse.getBody());
			return false;
		}

	}


	@Override
	public boolean processInfoAccessToPractitioner(User user,String jsonInput) throws JSONException {
		logger.debug("Entering processInfoAccessToPractitioner() POSTing url:" + URL+user.getPort()+"/org.acme.model.ProcessInfoAccessToPractitioner");
		
		JSONObject input = new JSONObject(jsonInput);
		JSONObject toSend = new JSONObject();
		String patient = (String)  input.get("patient");
		String person =  (String) input.get("accessor");
		String action =  (String) input.get("a");
		
		toSend.put("$class", "org.acme.model.ProcessInfoAccessToPractitioner");
		toSend.put("patient", patient);
		toSend.put("practitioner", person);
		toSend.put("a", action);
		logger.debug("processMCAccess() tosend returning: "+ toSend.toString());

		HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);
		ResponseEntity<String> composerResponse = restTemplate
				.exchange(URL+user.getPort()+"/api/org.acme.model.ProcessInfoAccessToPractitioner", HttpMethod.POST, entity, String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("processInfoAccessToPractitioner() returning: " + composerResponse.getBody());
			return true;
		}
		logger.debug("Exiting processInfoAccessToPractitioner() with null value with error: " + composerResponse.getBody());
		return false;
	}

	@Override
	public boolean fulfilPre(User user,String jsonInput) throws JSONException {
		logger.debug("Entering fulfilPre()" +" as " + user.getUsername()  +" POSTing url:" + URL+user.getPort()+"/org.acme.model.FulfilPre");
		JSONObject input = new JSONObject(jsonInput);
		JSONObject toSend = new JSONObject();

		toSend.put("$class", "org.acme.model.FulfilPre");
		toSend.put("pre", input.getJSONObject("pres").get("preId"));
		logger.debug("Entering fulfilPre() POSTing" +toSend.toString());
		HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);

		ResponseEntity<String> composerResponse = restTemplate
				.exchange(URL+user.getPort()+"/api/org.acme.model.FulfilPre", HttpMethod.POST, entity, String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("fulfilPre() returning: " + composerResponse.getBody());
			return true;
		}
		logger.debug("Exiting fulfilPre() with null value with error: " + composerResponse.getBody());
		return false;
	}

	@Override
	public boolean issueMC(User user,String jsonInput) throws JSONException {
		logger.debug("Entering issueMC() POSTing url:" + URL+user.getPort()+"/org.acme.model.MedicalCert");
		UUID idOne = UUID.randomUUID();
		JSONObject input = new JSONObject(jsonInput);
		JSONObject toSend = new JSONObject();
		String owner = ((String) input.get("issuer")).split("#")[1];

		toSend.put("$class", "org.acme.model.MedicalCert");
		toSend.put("mcId", idOne.toString());
		toSend.put("duration",input.get("duration"));
		toSend.put("startDate", input.get("startDate"));
		toSend.put("remark", input.get("remark"));
		toSend.put("issuee", input.get("issuee"));
		toSend.put("issuer", owner);
		logger.debug("Entering issueMC() POSTingSENDTO " + toSend.toString());


		HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);
		ResponseEntity<String> composerResponse = restTemplate
				.exchange(URL+user.getPort()+"/api/org.acme.model.MedicalCert", HttpMethod.POST, entity, String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("issueMC() returning: " + composerResponse.getBody());
			return true;
		}
		logger.debug("Exiting issueMC() with null value with error: " + composerResponse.getBody());
		return false;
	}

	@Override
	public boolean issueMedInfo(User user,String jsonInput) throws JSONException {
		logger.debug("Entering issueMedInfo() POSTing url:" + URL+user.getPort()+"/org.acme.model.MedicalInfo");
		UUID idOne = UUID.randomUUID();
		JSONObject input = new JSONObject(jsonInput);
		JSONObject toSend = new JSONObject();
		logger.debug("Entering JSONINPUT: " + jsonInput);

		String owner = ((String) input.get("issuer")).split("#")[1];

		toSend.put("$class", "org.acme.model.MedicalInfo");
		toSend.put("infoId", idOne.toString());
		toSend.put("issuer", owner);
		toSend.put("owner", input.get("issuee"));
		toSend.put("date", input.get("date"));
		if ( input.get("diagnosis")!= null || !input.get("diagnosis").equals("")){
			toSend.put("diagnosis", input.get("diagnosis"));
		}

		if ( input.get("testImages")!= null || !input.get("testImages").equals("")){
			toSend.put("testImages", input.get("testImages"));
		}

		if ( input.get("labReports")!= null || !input.get("labReports").equals("")){
			toSend.put("labReports", input.get("labReports"));

		}
		if ( input.get("drugAllergies")!= null || !input.get("drugAllergies").equals("")){
			toSend.put("drugAllergies", input.get("drugAllergies"));
		}
		logger.debug("issueMC() returningTOSEND: " + toSend.toString());

		HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);
		ResponseEntity<String> composerResponse = restTemplate
				.exchange(URL+user.getPort()+"/api/org.acme.model.MedicalInfo", HttpMethod.POST, entity, String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("issueMC() returning: " + composerResponse.getBody());
			return true;
		}
		logger.debug("Exiting issueMC() with null value with error: " + composerResponse.getBody());
		return false;
	}

	@Override
	public boolean issuePrescription(User user,String jsonInput) throws JSONException {
		logger.debug("Entering issuePrescription() POSTing url:" + URL+user.getPort()+"/org.acme.model.Prescription");
		UUID idOne = UUID.randomUUID();
		JSONObject input = new JSONObject(jsonInput);
		JSONObject toSend = new JSONObject();
		String issuer = ((String) input.get("issuer")).split("#")[1];

		toSend.put("$class", "org.acme.model.Prescription");
		toSend.put("preId", idOne.toString());
		toSend.put("fulfilled", false);
		toSend.put("issuer", issuer);
		toSend.put("issuee", input.get("issuee"));
		toSend.put("date", input.get("date"));
		JSONArray arr = input.getJSONArray("drugs");
		JSONArray ja = new JSONArray();

		for (int i = 0; i < arr.length(); i++) {
			JSONObject temp= arr.getJSONObject(i);
			if (temp.get("drugName") == null || temp.get("drugName").equals("") ){
				continue;
			}
			JSONObject jo = new JSONObject();
			jo.put("$class", "org.acme.model.Drug");
			jo.put("drugName", temp.get("drugName"));
			jo.put("drugQty", temp.get("drugQty"));
			ja.put(jo);
		}		

		toSend.put("drugs", ja);

		logger.debug("issueMC() returningTOSEND: " + toSend.toString());

		HttpEntity<String> entity = new HttpEntity<String>(toSend.toString(), JSONhttpHeaders);
		ResponseEntity<String> composerResponse = restTemplate
				.exchange(URL+user.getPort()+"/api/org.acme.model.Prescription", HttpMethod.POST, entity, String.class);
		if (composerResponse.getStatusCode() == HttpStatus.OK) {
			logger.debug("issuePrescription() returning: " + composerResponse.getBody());
			return true;
		}
		logger.debug("Exiting issuePrescription() with null value with error: " + composerResponse.getBody());
		return false;
	}

}