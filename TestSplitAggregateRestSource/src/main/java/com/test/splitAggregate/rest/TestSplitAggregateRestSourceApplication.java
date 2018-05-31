package com.test.splitAggregate.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.test.splitAggregate.common", "com.test.splitAggregate.rest"})
public class TestSplitAggregateRestSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSplitAggregateRestSourceApplication.class, args);
	}
}
