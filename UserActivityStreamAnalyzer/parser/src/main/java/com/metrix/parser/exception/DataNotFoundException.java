package com.metrix.parser.exception;

public class DataNotFoundException extends Throwable {
    String message;

    public DataNotFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return "DataNotFoundException [message=" + message + "]";
    }
}