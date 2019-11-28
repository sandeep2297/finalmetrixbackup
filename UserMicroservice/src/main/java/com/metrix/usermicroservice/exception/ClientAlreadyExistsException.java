package com.metrix.usermicroservice.exception;

public class ClientAlreadyExistsException extends Exception {

    String message;

    public ClientAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    public ClientAlreadyExistsException() {
        super();
    }
}
