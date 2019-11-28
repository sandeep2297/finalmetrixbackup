/**
 * Netflix Zuul API Gateway Server Gateway Controller
 */

package com.metrix.apigatewayserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GatewayController {

    /**
     *
     *
     * Method to display Web App Microservice for  Netflix Zuul API Gateway Server Gateway Controller
     */
    @GetMapping("/")
    public String display() {
        return "forward://WebAppMicroservice";
    }

}
