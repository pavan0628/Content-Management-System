package com.example.cms.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserNotFoundException extends RuntimeException{
	private String message;

	public String getMessage() {
		return message;
	}
}
