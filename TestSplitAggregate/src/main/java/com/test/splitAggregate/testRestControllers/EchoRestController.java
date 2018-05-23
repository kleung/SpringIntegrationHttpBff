package com.test.splitAggregate.testRestControllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.splitAggregate.exception.TestException;

@RestController
@RequestMapping("/api")
public class EchoRestController {
	
	public EchoRestController() {
		super();
	}

	@RequestMapping(value="/echo/{value}", method=RequestMethod.GET, produces="text/plain")
	public String echo(@PathVariable("value")String value) {
		/*try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TestException(e.getMessage(), e);
		}*/
		return value;
	}
}
