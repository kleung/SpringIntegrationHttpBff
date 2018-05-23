package com.test.splitAggregate.messaging.split;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import com.test.splitAggregate.constants.TestSplitAggregateConstants;

import java.util.ArrayList;
import java.util.Date;

public class TestSplitter {
	
	protected List<String> testSplitDestinations;
	
	public TestSplitter() {
		super();
	}
	
	public void setTestSplitDestinations(List<String> testSplitDestinations) {
		this.testSplitDestinations = testSplitDestinations;
	}

	protected Message cloneMessage(String destination, 
									Object correlationId,
									Date startTime,
									Object payload) {
		
		Message result = MessageBuilder.withPayload(payload)
										.setCorrelationId(correlationId)
										.setHeader(TestSplitAggregateConstants.splitMessageDestinationHeaderName, destination)
										.setHeader(TestSplitAggregateConstants.startTime, startTime)
										.build();
		
		return result;
	}
	
	
	public List<Message> splitMessage(Message message) {
		List<Message> result = new ArrayList<Message>();
		MessageHeaders headers = message.getHeaders();
		Object correlationId = headers.getId();
		Date startTime = (Date)headers.get(TestSplitAggregateConstants.startTime);
		Object payload = message.getPayload();
		
		for(String destination: this.testSplitDestinations) {
			result.add(this.cloneMessage(destination, correlationId, startTime, payload));
		}
			
		return result;
	}

}
