package com.metrix.awardsmicroservice.libs.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document(collection = "Badge")
public class Badge {

    @Id
    String id;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "Badge Id")
    private String badgeId;
    @ApiModelProperty(notes = "Badges for a particular Client")
    private String clientId;
    @ApiModelProperty(notes = "Badge Type")
    private String type;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "Badge Name")
    private String name;
    @ApiModelProperty(notes = "Badge Description")
    private String description;
    @ApiModelProperty(notes = "Badge Image")
    private String image;
    @ApiModelProperty(notes = "Badge Criteria")
    private String criteria;
    @ApiModelProperty(notes = "List of Badge Alignments")
    private ArrayList<Alignment> alignment;
    @ApiModelProperty(notes = "List of Badge Tags")
    private ArrayList<String> tags;
    @ApiModelProperty(notes = "Created By")
    private String createdBy;
    @ApiModelProperty(notes = "Created On")
    private LocalDateTime createdOn;
    @ApiModelProperty(notes = "Updated By")
    private String updatedBy;
    @ApiModelProperty(notes = "Updated On")
    private LocalDateTime updatedOn;

    public Badge() {
    }

    public Badge(String id, String badgeId, String clientId, String type, String name, String description, String image, String criteria, ArrayList<Alignment> alignment, ArrayList<String> tags, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn) {
        this.id = id;
        this.badgeId = badgeId;
        this.clientId = clientId;
        this.type = type;
        this.name = name;
        this.description = description;
        this.image = image;
        this.criteria = criteria;
        this.alignment = alignment;
        this.tags = tags;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public ArrayList<Alignment> getAlignment() {
        return alignment;
    }

    public void setAlignment(ArrayList<Alignment> alignment) {
        this.alignment = alignment;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "id='" + id + '\'' +
                ", badgeId='" + badgeId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", criteria='" + criteria + '\'' +
                ", alignment=" + alignment +
                ", tags=" + tags +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                '}';
    }
}