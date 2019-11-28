package com.metrix.libs.model;

import java.util.ArrayList;

public class RuleActivityData {
    ActivityRule activityRule;
    ActivityRuleEvaluationResults results;
    ArrayList<Expression> expressionArrayList;

    public RuleActivityData(ActivityRule activityRule, ActivityRuleEvaluationResults results, ArrayList<Expression> expressionArrayList) {
        this.activityRule = activityRule;
        this.results = results;
        this.expressionArrayList = expressionArrayList;
    }

    public RuleActivityData() {
    }

    public ActivityRule getActivityRule() {
        return activityRule;
    }

    public void setActivityRule(ActivityRule activityRule) {
        this.activityRule = activityRule;
    }

    public ActivityRuleEvaluationResults getResults() {
        return results;
    }

    public void setResults(ActivityRuleEvaluationResults results) {
        this.results = results;
    }

    public ArrayList<Expression> getExpressionArrayList() {
        return expressionArrayList;
    }

    public void setExpressionArrayList(ArrayList<Expression> expressionArrayList) {
        this.expressionArrayList = expressionArrayList;
    }

    @Override
    public String toString() {
        return "RuleActivityData{" +
                "activityRule=" + activityRule +
                ", results=" + results +
                ", expressionArrayList=" + expressionArrayList +
                '}';
    }
}
