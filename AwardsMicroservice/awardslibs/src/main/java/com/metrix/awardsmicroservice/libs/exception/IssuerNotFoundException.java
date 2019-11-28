package com.metrix.awardsmicroservice.libs.exception;

public class IssuerNotFoundException extends Exception {
    String message;

    public IssuerNotFoundException (String message) {
        super(message);
        this.message = message;
    }
}
