package com.metrix.libs.model;

import java.time.LocalDateTime;

public class Awards {
    private String awardId;
    private String awardType;
    private String awardValue;
    private LocalDateTime awardedOn;

    public Awards() {
    }

    public Awards(String awardId, String awardType, String awardValue, LocalDateTime awardedOn) {
        this.awardId = awardId;
        this.awardType = awardType;
        this.awardValue = awardValue;
        this.awardedOn = awardedOn;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
    }

    public String getAwardValue() {
        return awardValue;
    }

    public void setAwardValue(String awardValue) {
        this.awardValue = awardValue;
    }

    public LocalDateTime getAwardedOn() {
        return awardedOn;
    }

    public void setAwardedOn(LocalDateTime awardedOn) {
        this.awardedOn = awardedOn;
    }

    @Override
    public String toString() {
        return "Awards{" +
                "awardId=" + awardId +
                ", awardType='" + awardType + '\'' +
                ", awardValue='" + awardValue + '\'' +
                ", awardedOn=" + awardedOn +
                '}';
    }
}
