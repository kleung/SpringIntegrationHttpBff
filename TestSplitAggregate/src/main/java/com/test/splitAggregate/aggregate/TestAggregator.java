package com.test.splitAggregate.aggregate;

import java.util.Date;
import java.util.List;

import org.springframework.integration.store.MessageGroup;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import com.test.splitAggregate.constants.TestSplitAggregateConstants;
import com.test.splitAggregate.model.AggregateResult;

public class TestAggregator {
	
	public TestAggregator() {
		super();
	}
	
	public AggregateResult aggregate(List<Message> messages) {
		AggregateResult result = new AggregateResult();
		
		Message firstMessage = messages.get(0);
		MessageHeaders headers = firstMessage.getHeaders();
		Date startTime = (Date)headers.get(TestSplitAggregateConstants.startTime);
		String originalMessage = (String)headers.get(TestSplitAggregateConstants.originalMessage);
		
		result.setStartTime(startTime);
		result.setOriginalMessage(originalMessage);
		
		for(Message message : messages) {
			headers = message.getHeaders();
			
			String destination = (String)headers.get(TestSplitAggregateConstants.splitMessageDestinationHeaderName);
			
			if(destination.compareToIgnoreCase("echo") == 0) {
				result.setEchoResult((String)message.getPayload());
			} else if(destination.compareToIgnoreCase("inputDependent") == 0) {
				result.setRequestDependentResult((String)message.getPayload());
			}
		}
		
		Date endTime = new Date();
		result.setEndTime(endTime);
		
		return result;
	}

}
