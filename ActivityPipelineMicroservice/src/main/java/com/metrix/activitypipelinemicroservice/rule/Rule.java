package com.metrix.activitypipelinemicroservice.rule;

import java.util.ArrayList;

public class Rule {
    String activityPipelineId;
    String ruleName;
    String ruleDescription;
    ArrayList<RuleExpression> expression;
    String point;
    String badge;
    String createdBy;

    public Rule() {
    }

    public Rule(String activityPipelineId, String ruleName, String ruleDescription, ArrayList<RuleExpression> expression, String point, String badge, String createdBy) {
        this.activityPipelineId = activityPipelineId;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.expression = expression;
        this.point = point;
        this.badge = badge;
        this.createdBy = createdBy;
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

    public ArrayList<RuleExpression> getExpression() {
        return expression;
    }

    public void setExpression(ArrayList<RuleExpression> expression) {
        this.expression = expression;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "activityPipelineId='" + activityPipelineId + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                ", expression=" + expression +
                ", point='" + point + '\'' +
                ", badge='" + badge + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
