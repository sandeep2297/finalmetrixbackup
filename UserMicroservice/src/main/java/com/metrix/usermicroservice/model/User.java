package com.metrix.usermicroservice.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class User {

    @Id
    private String id;
    @Indexed
    @ApiModelProperty(notes = "User role in the system")
    userRole role;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "User Google username")
    private String userName;
    @ApiModelProperty(notes = "User First Name")
    private String firstName;
    @ApiModelProperty(notes = "User Full Name")
    private String name;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "User Google email id")
    private String email;
    @ApiModelProperty(notes = "User Google Profile Photo")
    private String avatarURL;
    @ApiModelProperty(notes = "Created At")
    private LocalDateTime createdAt;
    @ApiModelProperty(notes = "Created By")
    private String createdBy;
    @ApiModelProperty(notes = "Updated At")
    private LocalDateTime updatedAt;
    @ApiModelProperty(notes = "Updated By")
    private String updatedBy;
    @ApiModelProperty(notes = "T&C Update")
    private boolean read;

    public enum userRole {
        SUPERADMIN, MAINTAINER, CLIENT
    }

    public userRole getRole() {
        return role;
    }

    public void setRole(userRole role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public User(String userName, String firstName, String name, String email, String avatarURL, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, boolean read) {
        this.userName = userName;
        this.firstName = firstName;
        this.name = name;
        this.email = email;
        this.avatarURL = avatarURL;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.read = read;
    }

    public User() {
        super();
    }
}
