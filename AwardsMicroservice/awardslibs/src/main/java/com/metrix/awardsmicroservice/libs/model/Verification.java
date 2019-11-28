package com.metrix.awardsmicroservice.libs.model;

import java.util.ArrayList;

public class Verification {

    private String type;
    private String verificationProperty;
    private String startsWith;
    private ArrayList<String> allowedOrigins;

    public Verification() {
    }

    public Verification(String type, String verificationProperty, String startsWith, ArrayList<String> allowedOrigins) {
        this.type = type;
        this.verificationProperty = verificationProperty;
        this.startsWith = startsWith;
        this.allowedOrigins = allowedOrigins;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVerificationProperty() {
        return verificationProperty;
    }

    public void setVerificationProperty(String verificationProperty) {
        this.verificationProperty = verificationProperty;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public ArrayList<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(ArrayList<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    @Override
    public String toString() {
        return "Verification{" +
                "type='" + type + '\'' +
                ", verificationProperty='" + verificationProperty + '\'' +
                ", startsWith='" + startsWith + '\'' +
                ", allowedOrigins=" + allowedOrigins +
                '}';
    }
}