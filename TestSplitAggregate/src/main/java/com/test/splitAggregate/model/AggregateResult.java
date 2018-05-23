package com.test.splitAggregate.model;

import java.io.Serializable;
import java.util.Date;

public class AggregateResult implements Serializable {
	
	private static final long serialVersionUID = -6583460401704349945L;
	
	protected String echoResult;
	
	protected String requestDependentResult;
	
	protected Date startTime;
	
	protected Date endTime;
	
	protected String originalMessage;
	
	public AggregateResult() {
		super();
	}

	public String getEchoResult() {
		return echoResult;
	}

	public void setEchoResult(String echoResult) {
		this.echoResult = echoResult;
	}

	public String getRequestDependentResult() {
		return requestDependentResult;
	}

	public void setRequestDependentResult(String requestDependentResult) {
		this.requestDependentResult = requestDependentResult;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getOriginalMessage() {
		return originalMessage;
	}

	public void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}

}
