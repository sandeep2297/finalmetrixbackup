package com.metrix.awardsmicroservice.libs.exception;

public class BadgeClassNotFoundException extends Exception {
    String message;

    public BadgeClassNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
