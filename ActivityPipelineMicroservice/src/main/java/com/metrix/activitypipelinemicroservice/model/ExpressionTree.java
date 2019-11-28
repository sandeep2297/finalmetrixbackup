package com.metrix.activitypipelinemicroservice.model;

import io.swagger.annotations.ApiModelProperty;

public class ExpressionTree {

    @ApiModelProperty(notes = "Criteria Id")
    String expressionId;
    @ApiModelProperty(notes = "Conjunction")
    String andWith;
    @ApiModelProperty(notes = "Conjunction")
    String orWith;

    public ExpressionTree() {
    }

    public ExpressionTree(String expressionId, String andWith, String orWith) {
        this.expressionId = expressionId;
        this.andWith = andWith;
        this.orWith = orWith;
    }

    public String getExpressionId() {
        return expressionId;
    }

    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }

    public String getAndWith() {
        return andWith;
    }

    public void setAndWith(String andWith) {
        this.andWith = andWith;
    }

    public String getOrWith() {
        return orWith;
    }

    public void setOrWith(String orWith) {
        this.orWith = orWith;
    }

    @Override
    public String toString() {
        return "ExpressionTree{" +
                "expressionId='" + expressionId + '\'' +
                ", andWith='" + andWith + '\'' +
                ", orWith='" + orWith + '\'' +
                '}';
    }
}
