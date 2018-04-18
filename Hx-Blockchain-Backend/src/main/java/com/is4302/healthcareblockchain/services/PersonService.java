package com.is4302.healthcareblockchain.services;

import org.json.JSONException;
import org.json.JSONObject;

import com.is4302.healthcareblockchain.entities.User;

public interface PersonService {

	String getPersonalDetails(User user) throws JSONException;

	boolean updatePersonalDetails(User user, String jsonInput) throws JSONException;

	String getMedicalCerts(User user) throws JSONException;

	String getMedicalInfo(User user) throws JSONException;

	String getPrescriptions(User user) throws JSONException;

	String getPatientInfo(User user) throws JSONException;
	
	String getPharmacy(User user) throws JSONException;
	
	String getDoctor(User user) throws JSONException;
	
	String getPatient(User user) throws JSONException;

	boolean processInfoAccessToPractitioner(User user, String jsonInput) throws JSONException;

	boolean fulfilPre(User user, String jsonInput) throws JSONException;

	boolean issueMC(User user, String jsonInput) throws JSONException;

	boolean issuePrescription(User user, String jsonInput) throws JSONException;

	boolean processPreAccess(User user, String jsonInput) throws JSONException;

	boolean issueMedInfo(User user, String jsonInput) throws JSONException;

	boolean processMCAccess(User user, String jsonInput) throws JSONException;	

}
