package com.musalasoft.application.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.musalasoft.application.response.APIError;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResorceNotFoundException(ResourceNotFoundException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		APIError error = APIError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND)
				.message("Resource Not Found")
				.errors(details)
				.build();
		
		return ResponseEntityBuilder.build(error);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		APIError error = APIError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST)
				.message("Constraint Violation")
				.errors(details)
				.build();
		
		return ResponseEntityBuilder.build(error);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(CustomException ex) {
		List<String> details = null;
		
		APIError error = APIError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST)
				.message(ex.getMessage())
				.errors(details)
				.build();
		
		return ResponseEntityBuilder.build(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleJdbcSQLIntegrityConstraintViolationException(DataIntegrityViolationException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		APIError error = APIError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST)
				.message("Constraint Violation")
				.errors(details)
				.build();
		
		return ResponseEntityBuilder.build(error);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<Object> handleIOException(IOException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		APIError error = APIError.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST)
				.message("IO Exeption occured")
				.errors(details)
				.build();
		
		return ResponseEntityBuilder.build(error);
	}
}
