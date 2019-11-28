package com.metrix.awardsmicroservice.libs.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class IssuerProfile {

    public Status status;
    private String issuerProfileId;
    private String issuerName;
    private String issuerEmail;
    private String clientId;
    private String entityType;
    private String issuerAvatarURL;
    private String issuerOrganizationURL;
    private String issuerDescription;
    private String profileObjective;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdOn;
    private String createdBy;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedOn;
    private String updatedBy;

    public IssuerProfile() {
    }

    public IssuerProfile(String issuerProfileId) {
        this.issuerProfileId = issuerProfileId;
    }

    public IssuerProfile(Status status, String issuerProfileId, String issuerName, String issuerEmail, String clientId, String entityType, String issuerAvatarURL, String issuerOrganizationURL, String issuerDescription, String profileObjective, LocalDateTime createdOn, String createdBy, LocalDateTime updatedOn, String updatedBy) {
        this.status = status;
        this.issuerProfileId = issuerProfileId;
        this.issuerName = issuerName;
        this.issuerEmail = issuerEmail;
        this.clientId = clientId;
        this.entityType = entityType;
        this.issuerAvatarURL = issuerAvatarURL;
        this.issuerOrganizationURL = issuerOrganizationURL;
        this.issuerDescription = issuerDescription;
        this.profileObjective = profileObjective;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIssuerProfileId() {
        return issuerProfileId;
    }

    public void setIssuerProfileId(String issuerProfileId) {
        this.issuerProfileId = issuerProfileId;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getIssuerEmail() {
        return issuerEmail;
    }

    public void setIssuerEmail(String issuerEmail) {
        this.issuerEmail = issuerEmail;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getIssuerAvatarURL() {
        return issuerAvatarURL;
    }

    public void setIssuerAvatarURL(String issuerAvatarURL) {
        this.issuerAvatarURL = issuerAvatarURL;
    }

    public String getIssuerOrganizationURL() {
        return issuerOrganizationURL;
    }

    public void setIssuerOrganizationURL(String issuerOrganizationURL) {
        this.issuerOrganizationURL = issuerOrganizationURL;
    }

    public String getIssuerDescription() {
        return issuerDescription;
    }

    public void setIssuerDescription(String issuerDescription) {
        this.issuerDescription = issuerDescription;
    }

    public String getProfileObjective() {
        return profileObjective;
    }

    public void setProfileObjective(String profileObjective) {
        this.profileObjective = profileObjective;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "IssuerProfile{" +
                "status=" + status +
                ", issuerProfileId='" + issuerProfileId + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", issuerEmail='" + issuerEmail + '\'' +
                ", clientId='" + clientId + '\'' +
                ", entityType='" + entityType + '\'' +
                ", issuerAvatarURL='" + issuerAvatarURL + '\'' +
                ", issuerOrganizationURL='" + issuerOrganizationURL + '\'' +
                ", issuerDescription='" + issuerDescription + '\'' +
                ", profileObjective='" + profileObjective + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
