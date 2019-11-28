package com.metrix.activitypipelinemicroservice.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document(collection = "ActivityRule")
public class ActivityRule {

    @Id
    private String Id;
    @ApiModelProperty(notes = "Activity Rule Id")
    @Indexed(unique = true)
    private String activityRuleId;
    @ApiModelProperty(notes = "Activity Pipeline Id")
    private String activityPipelineId;
    @ApiModelProperty(notes = "Pipeline Archieve Status")
    private boolean isArchieved;
    @ApiModelProperty(notes = "Activity Rule Version")
    private String ruleName;
    @ApiModelProperty(notes = "Activity Rule Description")
    private String ruleDescription;
    @ApiModelProperty(notes = "Expression Tree")
    private ArrayList<ExpressionTree> expressionTree;
    @ApiModelProperty(notes = "Award")
    private ArrayList<Award> award;
    @ApiModelProperty(notes = "Created By")
    private String createdBy;
    @ApiModelProperty(notes = "Created On")
    private LocalDateTime createdOn;
    @ApiModelProperty(notes = "Updated On")
    private LocalDateTime updatedOn;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public boolean isArchieved() {
        return isArchieved;
    }

    public void setArchieved(boolean archieved) {
        isArchieved = archieved;
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
