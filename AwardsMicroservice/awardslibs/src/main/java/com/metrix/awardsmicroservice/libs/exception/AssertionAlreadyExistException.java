package com.metrix.awardsmicroservice.libs.exception;

public class AssertionAlreadyExistException extends Exception {
    String message;

    public AssertionAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}