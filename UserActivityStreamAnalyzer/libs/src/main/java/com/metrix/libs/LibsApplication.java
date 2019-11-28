package com.metrix.libs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.metrix.libs")
public class LibsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibsApplication.class, args);
    }

}
