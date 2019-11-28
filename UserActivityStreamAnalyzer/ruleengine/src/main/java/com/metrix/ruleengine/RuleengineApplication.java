package com.metrix.ruleengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RuleengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuleengineApplication.class, args);
	}

}
