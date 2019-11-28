package com.metrix.activitypipelinemicroservice.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;

public class BadgeClass {
    @Indexed(unique = true)
    @ApiModelProperty(notes = "Badge Id")
    private String badgeId;
    @ApiModelProperty(notes = "Badge Type")
    private String type;
    @ApiModelProperty(notes = "Badge Name")
    private String name;
    @ApiModelProperty(notes = "Badge Description")
    private String description;
    @ApiModelProperty(notes = "Badge Image")
    private String image;
    @ApiModelProperty(notes = "Badge Criteria")
    private String criteria;
    @ApiModelProperty(notes = "Badge Issuer")
    private String issuer;
    @ApiModelProperty(notes = "List of Badge Alignments")
    private ArrayList<Alignment> alignment;
    @ApiModelProperty(notes = "List of Badge Tags")
    private ArrayList<String> tags;

    public BadgeClass() {
    }

    public String getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(String badgeId) {
        this.badgeId = badgeId;
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

    @Override
    public String toString() {
        return "BadgeClass{" +
                "badgeId='" + badgeId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", criteria='" + criteria + '\'' +
                ", issuer='" + issuer + '\'' +
                ", alignment=" + alignment +
                ", tags=" + tags +
                '}';
    }
}
