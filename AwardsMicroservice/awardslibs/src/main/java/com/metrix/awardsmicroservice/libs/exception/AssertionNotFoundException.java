package com.metrix.awardsmicroservice.libs.exception;

public class AssertionNotFoundException extends Exception {
    String message;

    public AssertionNotFoundException (String message) {
        super(message);
        this.message = message;
    }
}
