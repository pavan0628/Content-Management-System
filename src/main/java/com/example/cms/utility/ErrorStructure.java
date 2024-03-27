package com.example.cms.utility;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class ErrorStructure<T> {
	private int statusCode;
	private String errorMessage;
	private T rootCause;
	
	public ErrorStructure<T> setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	public ErrorStructure<T> setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}
	public ErrorStructure<T> setRootCause(T rootCause) {
		this.rootCause =  rootCause;
		return this;
	}
}
