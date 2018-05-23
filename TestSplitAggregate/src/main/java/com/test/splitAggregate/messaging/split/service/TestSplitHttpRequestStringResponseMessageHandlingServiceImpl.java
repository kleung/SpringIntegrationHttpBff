package com.test.splitAggregate.messaging.split.service;

import java.util.Date;

import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandlingException;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.client.HttpServerErrorException;

import com.test.splitAggregate.constants.TestSplitAggregateConstants;

public class TestSplitHttpRequestStringResponseMessageHandlingServiceImpl
			implements TestSplitHttpRequestStringResponseMessageHandlingService {
	
	protected MessagingTemplate messagingTemplate;
	
	protected MessageChannel callingChannel;
	
	public TestSplitHttpRequestStringResponseMessageHandlingServiceImpl() {
		super();
	}

	public void setCallingChannel(MessageChannel callingChannel) {
		this.callingChannel = callingChannel;
	}

	public void setMessagingTemplate(MessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public Message<String> handleSplitMessage(Message message) {
		IntegrationMessageHeaderAccessor accessor = new IntegrationMessageHeaderAccessor(message);
		MessageHeaders headers = message.getHeaders();
		Object correlationId = accessor.getCorrelationId();
		String destination = (String)headers.get(TestSplitAggregateConstants.splitMessageDestinationHeaderName);
		Date startTime = (Date)headers.get(TestSplitAggregateConstants.startTime);
		Object payload = message.getPayload();
		String resultPayload = null;
		Message<String> result = null;
		
		try {
			Message toOutboundGatewayMessage = MessageBuilder.withPayload(payload).build();
			
			Message response = this.messagingTemplate.sendAndReceive(this.callingChannel, message);
			
			
			if(response != null) {
				Object responsePayload = response.getPayload();
				
				if((responsePayload != null) && (responsePayload instanceof String)) {
					resultPayload = (String)responsePayload;
					
					result = MessageBuilder.withPayload(resultPayload)
										   .setCorrelationId(correlationId)
										   .setHeader(TestSplitAggregateConstants.splitMessageDestinationHeaderName, destination)
										   .setHeader(TestSplitAggregateConstants.startTime, startTime)
										   .setHeader(TestSplitAggregateConstants.originalMessage, payload)
										   .build();	   
				}
			}
																
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
