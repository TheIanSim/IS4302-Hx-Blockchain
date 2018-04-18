package com.is4302.healthcareblockchain.exceptions;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(JSONException.class)
	public ResponseEntity<String> handleAll(JSONException e) {
		logger.error("Unhandled JSON exception occurred", e);
		try {
			return new ResponseEntity<String>(new JSONObject()
					.put("Message", e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JSONException e1) {
			e1.printStackTrace();
			return null;
		}
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAll(Exception e) {
		logger.error("Unhandled exception occurred", e);
		try {
			return new ResponseEntity<String>(new JSONObject()
					.put("Message", e.getMessage()).toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JSONException e1) {
			e1.printStackTrace();
			return null;
		}

	}
}


