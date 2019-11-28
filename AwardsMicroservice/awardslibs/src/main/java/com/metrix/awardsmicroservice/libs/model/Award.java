package com.metrix.awardsmicroservice.libs.model;

import io.swagger.annotations.ApiModelProperty;

public class Award {
    @ApiModelProperty(notes = " Award Type")
    String awardType;
    @ApiModelProperty(notes = "Award Value")
    String awardValue;
    public Award() {
    }
    public Award(String awardType, String awardValue) {
        this.awardType = awardType;
        this.awardValue = awardValue;
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
    @Override
    public String toString() {
        return "Award{" +
                "awardType='" + awardType + '\'' +
                ", awardValue='" + awardValue + '\'' +
                '}';
    }
}

