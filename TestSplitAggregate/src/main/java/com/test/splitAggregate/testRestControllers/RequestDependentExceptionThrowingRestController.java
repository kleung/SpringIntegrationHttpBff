package com.test.splitAggregate.testRestControllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.splitAggregate.exception.TestException;

@RestController
@RequestMapping("/api")
public class RequestDependentExceptionThrowingRestController {
	
	public RequestDependentExceptionThrowingRestController() {
		super();
	}
	
	@RequestMapping(value="/checkRequest/{value}", method=RequestMethod.GET, produces="text/plain")
	public String handleRequest(@PathVariable("value")String value) {
		try {
			//Thread.sleep(3000);
			Boolean throwException = Boolean.valueOf(value);
			if(throwException.booleanValue()) {
				throw new TestException("received \"true\", throw an exception.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException(e.getMessage(), e);
		}
		
		return value;
	}

}
