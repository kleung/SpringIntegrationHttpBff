package com.test.splitAggregate.gateway;

import org.springframework.messaging.MessageHandlingException;

import com.test.splitAggregate.model.AggregateResult;

public interface TestSplitAggregateGateway {

	public AggregateResult processRequest(String input) throws MessageHandlingException;
	
}
