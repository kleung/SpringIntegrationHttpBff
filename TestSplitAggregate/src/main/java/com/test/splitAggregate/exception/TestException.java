package com.test.splitAggregate.exception;

public class TestException extends RuntimeException {
	
	private static final long serialVersionUID = 509487776834038816L;

	public TestException(String message) {
		super(message);
	}
	
	public TestException(String message, Throwable cause) {
		super(message, cause);
	}

}
