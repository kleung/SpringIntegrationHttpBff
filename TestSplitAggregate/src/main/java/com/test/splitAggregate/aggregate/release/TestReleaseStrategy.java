package com.test.splitAggregate.aggregate.release;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.aggregator.ReleaseStrategy;
import org.springframework.integration.store.MessageGroup;
import org.springframework.messaging.Message;

import com.test.splitAggregate.constants.TestSplitAggregateConstants;

public class TestReleaseStrategy implements ReleaseStrategy {
	
	protected List<String> testSplitDestinations;
	
	public TestReleaseStrategy() {
		super();
	}

	public void setTestSplitDestinations(List<String> testSplitDestinations) {
		this.testSplitDestinations = testSplitDestinations;
	}

	@Override
	public boolean canRelease(MessageGroup group) {
		Set<String> destinationSet = new HashSet<String>();
		destinationSet.addAll(this.testSplitDestinations);
		
		for(Message message : group.getMessages()) {
			String destination = (String)message.getHeaders().get(TestSplitAggregateConstants.splitMessageDestinationHeaderName);
			if(destinationSet.contains(destination)) {
				destinationSet.remove(destination);
			}
		}
		
		boolean result = destinationSet.isEmpty();
		
		return result;
	}

}
