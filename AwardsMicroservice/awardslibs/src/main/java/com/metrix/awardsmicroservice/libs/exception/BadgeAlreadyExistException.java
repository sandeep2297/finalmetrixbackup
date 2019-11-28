package com.metrix.awardsmicroservice.libs.exception;

public class BadgeAlreadyExistException extends Exception {

    String message;

    public BadgeAlreadyExistException(String message) {
        super(message);
        this.message = message;
    }

}