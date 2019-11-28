package com.metrix.libs.exception;

public class ResultsNotFoundException extends  Exception {
    String message;
    public ResultsNotFoundException(String  message)
    {
        super(message);
        this.message = message;
    }

}
