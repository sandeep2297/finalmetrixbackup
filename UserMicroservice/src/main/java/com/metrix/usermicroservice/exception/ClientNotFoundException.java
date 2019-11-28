package com.metrix.usermicroservice.exception;

public class ClientNotFoundException extends Exception {

    String message;

    public ClientNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ClientNotFoundException() {
        super();
    }

}
