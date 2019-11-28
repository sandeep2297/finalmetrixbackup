/**
 * Web Application Microservice Main Class
 */

package com.metrix.webappmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebAppMicroserviceApplication {

    /**
     *
     *
     * Web Application Microservice Main Method
     */
    public static void main(String[] args) {
        SpringApplication.run(WebAppMicroserviceApplication.class, args);
    }

}
