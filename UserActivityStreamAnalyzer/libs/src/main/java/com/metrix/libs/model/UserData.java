package com.metrix.libs.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

public class UserData {
    @ApiModelProperty(notes = "Actor Id")
    String actorId;
    @ApiModelProperty(notes = "Object Content")
    String objectContent;
    @ApiModelProperty(notes = "Object Content Type")
    String objectContentType;
    @ApiModelProperty(notes = "Verbs")
    String verbs;
    @ApiModelProperty(notes = "Target Object Type")
    String targetObjectType;
    @ApiModelProperty(notes = "Target Content")
    String targetContent;
    @ApiModelProperty(notes = "Actor Object Type")
    String actorObjectType;
    @ApiModelProperty(notes = "Award List")
    ArrayList<Award> awardArrayList;

    public UserData(String ocontent, String oObjectType, String verbs, String tobjectType, String tcontent,
                    String aObjectType, String aid) {
        super();
        this.objectContent = ocontent;
        this.objectContentType = oObjectType;
        this.verbs = verbs;
        this.targetObjectType = tobjectType;
        this.targetContent = tcontent;
        this.actorObjectType = aObjectType;
        this.actorId = aid;
    }

    public UserData() {
    }

    public UserData(String actorId, String verbs, String objectContentType, String targetObjectType) {
        this.actorId = actorId;
        this.verbs = verbs;
        this.objectContentType = objectContentType;
        this.targetObjectType = targetObjectType;
    }


    public String getObjectContent() {
        return objectContent;
    }

    public void setObjectContent(String objectContent) {
        this.objectContent = objectContent;
    }

    public String getObjectContentType() {
        return objectContentType;
    }

    public void setObjectContentType(String objectContentType) {
        this.objectContentType = objectContentType;
    }

    public String getVerbs() {
        return verbs;
    }

    public void setVerbs(String verbs) {
        this.verbs = verbs;
    }

    public String getTargetObjectType() {
        return targetObjectType;
    }

    public void setTargetObjectType(String targetObjectType) {
        this.targetObjectType = targetObjectType;
    }

    public String getTargetContent() {
        return targetContent;
    }

    public void setTargetContent(String targetContent) {
        this.targetContent = targetContent;
    }

    public String getActorObjectType() {
        return actorObjectType;
    }

    public void setActorObjectType(String actorObjectType) {
        this.actorObjectType = actorObjectType;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public ArrayList<Award> getAwardArrayList() {
        return awardArrayList;
    }

    public void setAwardArrayList(ArrayList<Award> awardArrayList) {
        this.awardArrayList = awardArrayList;
    }

    public String find(String something) {

        if (something.equals("ACTOR")) return getActorObjectType();

        if (something.equals("TYPE")) return getVerbs();

        if (something.equals("OBJECT")) return getObjectContentType();

        if (something.equals("TARGET")) return getTargetObjectType();

        return null;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "actorId='" + actorId + '\'' +
                ", objectContent='" + objectContent + '\'' +
                ", objectContentType='" + objectContentType + '\'' +
                ", verbs='" + verbs + '\'' +
                ", targetObjectType='" + targetObjectType + '\'' +
                ", targetContent='" + targetContent + '\'' +
                ", actorObjectType='" + actorObjectType + '\'' +
                ", awardArrayList=" + awardArrayList +
                '}';
    }
}
