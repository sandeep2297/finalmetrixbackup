package com.metrix.activitypipelinemicroservice.exception;

public class DataNotFoundException extends Exception {

    String messsage;

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String messsage) {
        this.messsage = messsage;
    }
}
