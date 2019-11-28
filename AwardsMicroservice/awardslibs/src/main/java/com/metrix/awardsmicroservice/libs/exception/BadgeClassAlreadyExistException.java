package com.metrix.awardsmicroservice.libs.exception;

public class BadgeClassAlreadyExistException extends Exception {
    String message;

    public BadgeClassAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
