package com.metrix.activitypipelinemicroservice.model;

public class Headers {
    private String headerField;
    private String headerValue;

    public Headers() {
    }

    public Headers(String headerField, String headerValue) {
        this.headerField = headerField;
        this.headerValue = headerValue;
    }

    public String getHeaderField() {
        return headerField;
    }

    public void setHeaderField(String headerField) {
        this.headerField = headerField;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }
}
