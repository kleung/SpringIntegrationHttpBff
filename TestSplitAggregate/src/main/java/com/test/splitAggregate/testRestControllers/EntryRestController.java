package com.test.splitAggregate.testRestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.test.splitAggregate.gateway.TestSplitAggregateGateway;
import com.test.splitAggregate.model.AggregateResult;

@RestController
public class EntryRestController {
	
	@Autowired
	@Qualifier("testInboundGateway")
	protected TestSplitAggregateGateway inboundGateway;
	
	public EntryRestController() {
		super();
	}
	
	@RequestMapping(value="/testService/{throwException}", method=RequestMethod.GET)
	public AggregateResult processRequest(@PathVariable("throwException")String throwException) {
		AggregateResult result = null;
		
		try {
			result = this.inboundGateway.processRequest(throwException);
		} catch (MessageHandlingException mhe) {
			Throwable cause = mhe.getCause();
			cause.printStackTrace();
			if(cause instanceof HttpServerErrorException) {
				HttpServerErrorException httpError = (HttpServerErrorException)cause;
				String responseBodyStr = httpError.getResponseBodyAsString();
				
				System.out.println(responseBodyStr);
			}
			
			throw (RuntimeException)cause;
		}
		
		return result;
	}
	
	
}
