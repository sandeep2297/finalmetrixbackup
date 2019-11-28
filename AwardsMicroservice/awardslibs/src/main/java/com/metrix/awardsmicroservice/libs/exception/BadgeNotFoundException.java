package com.metrix.awardsmicroservice.libs.exception;

public class BadgeNotFoundException extends Exception {
    String message;

    public BadgeNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}