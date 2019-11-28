package com.metrix.awardsmicroservice.libs.model;

import java.util.ArrayList;

public class RevocationList {

    private String revocationListId;
    private String type;
    private String issuer;
    private String revokedAssertions;

    public RevocationList() {
    }

    public RevocationList(String revocationListId, String type, String issuer, String revokedAssertions) {
        this.revocationListId = revocationListId;
        this.type = type;
        this.issuer = issuer;
        this.revokedAssertions = revokedAssertions;
    }

    public String getRevocationListId() {
        return revocationListId;
    }

    public void setRevocationListId(String revocationListId) {
        this.revocationListId = revocationListId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getRevokedAssertions() {
        return revokedAssertions;
    }

    public void setRevokedAssertions(String revokedAssertions) {
        this.revokedAssertions = revokedAssertions;
    }

    @Override
    public String toString() {
        return "RevocationList{" +
                "revocationListId='" + revocationListId + '\'' +
                ", type='" + type + '\'' +
                ", issuer='" + issuer + '\'' +
                ", revokedAssertions='" + revokedAssertions + '\'' +
                '}';
    }
}