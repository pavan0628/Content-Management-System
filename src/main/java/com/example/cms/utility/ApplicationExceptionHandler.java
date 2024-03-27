package com.example.cms.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cms.exception.EmailAlreadyPresentException;

import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	private ErrorStructure<String> structure;
	
	private ResponseEntity<ErrorStructure<String>> errorResponse(HttpStatus status, String message, String rootCause){
		return new ResponseEntity<ErrorStructure<String>>(structure.setStatusCode(status.value())
				.setErrorMessage(message)
				.setRootCause(rootCause),status);
	}

	@ExceptionHandler(EmailAlreadyPresentException.class)
	public ResponseEntity<ErrorStructure<String>> handleUserAlreadyExistByEmail(EmailAlreadyPresentException ex){
		return errorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),"User already exists with the given gamil");
	}
}


