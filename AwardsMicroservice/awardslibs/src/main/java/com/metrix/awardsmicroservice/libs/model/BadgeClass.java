package com.metrix.awardsmicroservice.libs.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "BadgeClass")
public class BadgeClass {

    @Indexed(unique = true)
    @ApiModelProperty(notes = "Badge Instance Id")
    private String badgeClassId;
    @ApiModelProperty(notes = "Badge Instance Type")
    private String type;
    @ApiModelProperty(notes = "Badge Instance Name")
    private String name;
    @ApiModelProperty(notes = "Badge Instance Description")
    private String description;
    @ApiModelProperty(notes = "Badge Instance Image")
    private String image;
    @ApiModelProperty(notes = "Badge Instance Criteria")
    private String criteria;
    @ApiModelProperty(notes = "Badge Instance Issuer")
    private String issuer;
    @ApiModelProperty(notes = "List of Badge Instance Alignments")
    private ArrayList<Alignment> alignment;
    @ApiModelProperty(notes = "List of Badge Instance Tags")
    private ArrayList<String> tags;

    public BadgeClass() {
    }

    public BadgeClass(String badgeClassId, String type, String name, String description, String image, String criteria, String issuer, ArrayList<Alignment> alignment, ArrayList<String> tags) {
        this.badgeClassId = badgeClassId;
        this.type = type;
        this.name = name;
        this.description = description;
        this.image = image;
        this.criteria = criteria;
        this.issuer = issuer;
        this.alignment = alignment;
        this.tags = tags;
    }

    public String getBadgeClassId() {
        return badgeClassId;
    }

    public void setBadgeClassId(String badgeClassId) {
        this.badgeClassId = badgeClassId;
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

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
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
}