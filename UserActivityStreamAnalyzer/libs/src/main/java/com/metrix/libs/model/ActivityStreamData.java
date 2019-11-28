package com.metrix.libs.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class ActivityStreamData {
    UserData userData;
    String issuerId;
    String pipelineId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime activityTime;

    public ActivityStreamData(UserData userData, String issuerId, String pipelineId, LocalDateTime activityTime) {
        this.userData = userData;
        this.issuerId = issuerId;
        this.pipelineId = pipelineId;
        this.activityTime = activityTime;
    }

    public ActivityStreamData() {
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }

    @Override
    public String toString() {
        return "ActivityStreamData{" +
                "userData=" + userData +
                ", issuerId='" + issuerId + '\'' +
                ", pipelineId='" + pipelineId + '\'' +
                ", activityTime=" + activityTime +
                '}';
    }
}
