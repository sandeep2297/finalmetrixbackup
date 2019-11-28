package com.metrix.activitypipelinemicroservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "IssuerProfile")
public class IssuerProfile {

    @ApiModelProperty(notes = "Status")
    public Status status;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "Issuer Profile Id")
    private String issuerProfileId;
    @ApiModelProperty(notes = "Issuer Name")
    private String issuerName;
    @ApiModelProperty(notes = "Issuer Email")
    private String issuerEmail;
    @ApiModelProperty(notes = "Client Id")
    private String clientId;
    @ApiModelProperty(notes = "Entity Type")
    private String entityType;
    @ApiModelProperty(notes = "Issuer Avatar URL")
    private String issuerAvatarURL;
    @ApiModelProperty(notes = "Issuer Organisation URL")
    private String issuerOrganizationURL;
    @ApiModelProperty(notes = "Issuer Description")
    private String issuerDescription;
    @ApiModelProperty(notes = "Issuer Profile Objective")
    private String profileObjective;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @ApiModelProperty(notes = "Created On")
    private LocalDateTime createdOn;
    @ApiModelProperty(notes = "Created By")
    private String createdBy;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @ApiModelProperty(notes = "Updated On")
    private LocalDateTime updatedOn;
    @ApiModelProperty(notes = "Updated By")
    private String updatedBy;

    public IssuerProfile() {
    }

    public IssuerProfile(String issuerProfileId, String issuerName, String issuerEmail, String clientId, String entityType, Status status, String issuerAvatarURL, String issuerOrganizationURL, String issuerDescription, String profileObjective, LocalDateTime createdOn, String createdBy, LocalDateTime updatedOn, String updatedBy) {
        this.issuerProfileId = issuerProfileId;
        this.issuerName = issuerName;
        this.issuerEmail = issuerEmail;
        this.clientId = clientId;
        this.entityType = entityType;
        this.status = status;
        this.issuerAvatarURL = issuerAvatarURL;
        this.issuerOrganizationURL = issuerOrganizationURL;
        this.issuerDescription = issuerDescription;
        this.profileObjective = profileObjective;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
