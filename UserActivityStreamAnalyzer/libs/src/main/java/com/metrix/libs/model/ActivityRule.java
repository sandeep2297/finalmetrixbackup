package com.metrix.libs.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ActivityRule implements Serializable {

    private String Id;
    @ApiModelProperty(notes = "Activity Rule Id")
    String activityRuleId;
    @ApiModelProperty(notes = "Activity Pipeline Id")
    String activityPipelineId;
    @ApiModelProperty(notes = "Pipeline Archieve Status")
    private boolean isArchieved;
    @ApiModelProperty(notes = "Activity Rule Name")
    String ruleName;
    @ApiModelProperty(notes = "Activity Rule Description")
    String ruleDescription;
    @ApiModelProperty(notes = "Expression Tree")
    ArrayList<ExpressionTree> expressionTree;
    @ApiModelProperty(notes = "Award")
    ArrayList<Award> award;
    @ApiModelProperty(notes = "Created By")
    private String createdBy;
    @ApiModelProperty(notes = "Created On")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime createdOn;
    @ApiModelProperty(notes = "Updated On")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime updatedOn;
    @ApiModelProperty(notes = "Updated By")
    private String updatedBy;

    public ActivityRule() {
    }

    public ActivityRule(String id, String activityRuleId, String activityPipelineId, boolean isArchieved, String ruleName, String ruleDescription, ArrayList<ExpressionTree> expressionTree, ArrayList<Award> award, String createdBy, LocalDateTime createdOn, LocalDateTime updatedOn, String updatedBy) {
        Id = id;
        this.activityRuleId = activityRuleId;
        this.activityPipelineId = activityPipelineId;
        this.isArchieved = isArchieved;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.expressionTree = expressionTree;
        this.award = award;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.updatedBy = updatedBy;
    }

    public String getActivityRuleId() {
        return activityRuleId;
    }

    public void setActivityRuleId(String activityRuleId) {
        this.activityRuleId = activityRuleId;
    }

    public String getActivityPipelineId() {
        return activityPipelineId;
    }

    public void setActivityPipelineId(String activityPipelineId) {
        this.activityPipelineId = activityPipelineId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public ArrayList<ExpressionTree> getExpressionTree() {
        return expressionTree;
    }

    public void setExpressionTree(ArrayList<ExpressionTree> expressionTree) {
        this.expressionTree = expressionTree;
    }

    public ArrayList<Award> getAward() {
        return award;
    }

    public void setAward(ArrayList<Award> award) {
        this.award = award;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean isArchieved() {
        return isArchieved;
    }

    public void setArchieved(boolean archieved) {
        isArchieved = archieved;
    }

    @Override
    public String toString() {
        return "ActivityRule{" +
                "Id='" + Id + '\'' +
                ", activityRuleId='" + activityRuleId + '\'' +
                ", activityPipelineId='" + activityPipelineId + '\'' +
                ", isArchieved=" + isArchieved +
                ", ruleName='" + ruleName + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                ", expressionTree=" + expressionTree +
                ", award=" + award +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}

