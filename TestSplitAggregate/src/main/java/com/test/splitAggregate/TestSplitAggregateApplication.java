package com.test.splitAggregate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value= {"classpath:/integration/test-split-aggregate.xml"})
public class TestSplitAggregateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSplitAggregateApplication.class, args);
	}
}
