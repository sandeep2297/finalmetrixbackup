/**
 * Activity Sink Microservice Controller
 */

package com.metrix.activitysinkmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivitySinkController {

    /**
     *
     *
     * Simple Welcome Method for Activity Sink Microservice Controller
     */
    @GetMapping("/")
    public String sayWelcome() {
        return "Welcome to Activity Sink Microservice!";
    }

}
