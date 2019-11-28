package com.metrix.libs.model;

import java.time.LocalDateTime;

public class DurationResult {
    int total_count;
    int passed;
    int failed;
    String issuerProfileId;
    LocalDateTime activityTime;

    public DurationResult(int total_count, int passed, int failed, String issuerProfileId, LocalDateTime activityTime) {
        this.total_count = total_count;
        this.passed = passed;
        this.failed = failed;
        this.issuerProfileId = issuerProfileId;
        this.activityTime = activityTime;
    }

    public DurationResult() {
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public String getIssuerProfileId() {
        return issuerProfileId;
    }

    public void setIssuerProfileId(String issuerProfileId) {
        this.issuerProfileId = issuerProfileId;
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }

    @Override
    public String toString() {
        return "DurationResult{" +
                "total_count=" + total_count +
                ", passed=" + passed +
                ", failed=" + failed +
                ", issuerProfileId='" + issuerProfileId + '\'' +
                ", activityTime=" + activityTime +
                '}';
    }
}
