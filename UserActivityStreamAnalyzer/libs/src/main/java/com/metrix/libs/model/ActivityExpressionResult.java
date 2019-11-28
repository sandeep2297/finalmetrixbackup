package com.metrix.libs.model;

public class ActivityExpressionResult {

    private String expressionId;
    private Boolean expressionResult;

    public ActivityExpressionResult() {
    }

    public ActivityExpressionResult(String expressionId, Boolean expressionResult) {
        this.expressionId = expressionId;
        this.expressionResult = expressionResult;
    }

    public String getExpressionId() {
        return expressionId;
    }

    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }

    public Boolean getExpressionResult() {
        return expressionResult;
    }

    public void setExpressionResult(Boolean expressionResult) {
        this.expressionResult = expressionResult;
    }

    @Override
    public String toString() {
        return "ActivityExpressionResult{" +
                "expressionId=" + expressionId +
                ", expressionResult=" + expressionResult +
                '}';
    }
}
