/**
 * Activity Pipeline Microservice Main Class
 */

package com.metrix.activitypipelinemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ActivityPipelineMicroserviceApplication {

    /**
     * Activity Pipeline Microservice Main Method
     */
    public static void main(String[] args) {
        SpringApplication.run(ActivityPipelineMicroserviceApplication.class, args);
    }

}
