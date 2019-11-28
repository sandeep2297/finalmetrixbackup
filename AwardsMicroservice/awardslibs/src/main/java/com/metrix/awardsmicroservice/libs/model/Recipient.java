package com.metrix.awardsmicroservice.libs.model;

public class Recipient {

    private String recipientId;
    private String type;
    private boolean hashed;
    private String salt;
    private String recipientName;

    public Recipient() {
    }

    public Recipient(String recipientId, String type, boolean hashed, String salt, String recipientName) {
        this.recipientId = recipientId;
        this.type = type;
        this.hashed = hashed;
        this.salt = salt;
        this.recipientName = recipientName;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHashed() {
        return hashed;
    }

    public void setHashed(boolean hashed) {
        this.hashed = hashed;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "recipientId='" + recipientId + '\'' +
                ", type='" + type + '\'' +
                ", hashed=" + hashed +
                ", salt='" + salt + '\'' +
                ", recipientName='" + recipientName + '\'' +
                '}';
    }
}
