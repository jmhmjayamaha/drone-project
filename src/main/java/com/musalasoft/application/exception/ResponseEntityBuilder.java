package com.musalasoft.application.exception;

import org.springframework.http.ResponseEntity;

import com.musalasoft.application.response.APIError;

public class ResponseEntityBuilder {

	public static ResponseEntity<Object> build(APIError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
