/**
 * Activity Sink Microservice Main Class
 */

package com.metrix.activitysinkmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActivitySinkMicroserviceApplication {

    /**
     *
     *
     * Activity Sink Microservice Main Method
     */
    public static void main(String[] args) {
        SpringApplication.run(ActivitySinkMicroserviceApplication.class, args);
    }

}
