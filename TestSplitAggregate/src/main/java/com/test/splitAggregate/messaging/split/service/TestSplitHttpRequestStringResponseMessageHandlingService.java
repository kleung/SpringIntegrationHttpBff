package com.test.splitAggregate.messaging.split.service;

import org.springframework.messaging.Message;

public interface TestSplitHttpRequestStringResponseMessageHandlingService {
	
	public Message<String> handleSplitMessage(Message message);

}
