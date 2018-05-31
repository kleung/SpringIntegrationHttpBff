package com.test.splitAggregate.common.exception.rest.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class ExceptionRestControllerAdvice {
	
	public ExceptionRestControllerAdvice() {
		super();
	}

	@ExceptionHandler(value= {RuntimeException.class, Exception.class})
	public ResponseEntity<String> handleRuntimeException(Exception e) {
		ResponseEntity<String> result = new ResponseEntity<String>(e.getMessage(),
																	HttpStatus.INTERNAL_SERVER_ERROR);
		
		return result;
	}
	
	@ExceptionHandler(value= {HttpServerErrorException.class})
	public ResponseEntity<String> handleWebException(HttpServerErrorException hse) {
		ResponseEntity<String> result = new ResponseEntity<String>(hse.getResponseBodyAsString(),
																	HttpStatus.INTERNAL_SERVER_ERROR);

		return result;
	}
}