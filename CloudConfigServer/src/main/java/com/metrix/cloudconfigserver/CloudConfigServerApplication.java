/**
 * Cloud Configuration Server Main Class
 */

package com.metrix.cloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
@EnableDiscoveryClient
public class CloudConfigServerApplication {

    /**
     *
     *
     * Cloud Configuration Server Main Method
     */
    public static void main(final String[] args) {
        SpringApplication.run(CloudConfigServerApplication.class, args);
    }

}
