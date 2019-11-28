package com.metrix.awardsmicroservice.awardbadge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.metrix.awardsmicroservice")
public class AwardbadgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwardbadgeApplication.class, args);
	}

}
