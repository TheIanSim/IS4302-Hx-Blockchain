package com.is4302.healthcareblockchain;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

	/**
	 * This event is executed as late as conceivably possible to indicate that 
	 * the application is ready to service requests.
	 */
	@Value("${composer.rest}")
	private String URL;	

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		RestTemplate restTemplate = event.getApplicationContext().getBean(RestTemplate.class);
		HttpHeaders JSONhttpHeaders = event.getApplicationContext().getBean(HttpHeaders.class);
		String jsonString = null;
		HttpEntity<String> entity = null;
		Resource resource = null;
		ResponseEntity<String> composerResponse = null;
		final int port = 3001;


		try {
			//Create first patient participant
			resource = new ClassPathResource("jsonData/patient1.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));	
			entity = new HttpEntity<String>(jsonString,JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.Patient", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Patient 1");
			//Create personal details for first patient
			resource = new ClassPathResource("jsonData/pd1.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.PersonalDetails", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created PD 1");
			//Create second patient participant
			resource = new ClassPathResource("jsonData/patient2.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString,JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.Patient", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Patient 2");
			//Create personal details for second patient
			resource = new ClassPathResource("jsonData/pd2.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.PersonalDetails", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created PD 2");
			//Create personal details for doctor1
			resource = new ClassPathResource("jsonData/pd3.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.PersonalDetails", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created PD 3");
			//Create personal details for doctor2
			resource = new ClassPathResource("jsonData/pd4.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.PersonalDetails", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created PD 4");
			//Create doctor1
			resource = new ClassPathResource("jsonData/doctor1.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.Practitioner", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Doctor 1");
			//Create doctor2
			resource = new ClassPathResource("jsonData/doctor2.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.Practitioner", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Doctor 2");
			//Create hospital1
			resource = new ClassPathResource("jsonData/hospital.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.Hospital", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Hospital 1");
			//Create pharm1
			resource = new ClassPathResource("jsonData/pharmacy.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.Pharmacy", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Pharmacy 1");
			//Create emp1
			resource = new ClassPathResource("jsonData/employer.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.Employer", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Employer 1");
			//Create medical cert 1
			resource = new ClassPathResource("jsonData/medcert.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.MedicalCert", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created MC 1");
			//Create medical in 1
			resource = new ClassPathResource("jsonData/medinfo.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.MedicalInfo", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Medical Info 1");
			//Create prescripton 1
			resource = new ClassPathResource("jsonData/prescription.json");
			jsonString = new String(Files.readAllBytes(Paths.get(resource.getURI())));			entity = new HttpEntity<String>(jsonString, JSONhttpHeaders);
			composerResponse = restTemplate
					.exchange(URL+port+"/api/org.acme.model.Prescription", HttpMethod.POST, entity, String.class);
			if (composerResponse.getStatusCode() != HttpStatus.OK) {
				throw new Exception("Error in the blockchain server");
			}
			System.out.println("Created Prescription 1");
		} catch (Exception e) {
			System.out.println("Error creating dummy data in the blockchain");
			e.printStackTrace();
		} 
		System.out.println("Application started successfully!");
		return;
	}

}