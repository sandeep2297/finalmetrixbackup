/**
 * Eureka Naming Server Main Class
 */

package com.metrix.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

    /**
     *
     *
     * Main Method for Eureka Naming Server
     */
    public static void main(final String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }

}
