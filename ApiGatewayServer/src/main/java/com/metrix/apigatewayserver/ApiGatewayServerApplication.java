package com.metrix.apigatewayserver;

import com.github.mthizo247.cloud.netflix.zuul.web.socket.EnableZuulWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulWebSocket
public class ApiGatewayServerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ApiGatewayServerApplication.class, args);
    }

}
