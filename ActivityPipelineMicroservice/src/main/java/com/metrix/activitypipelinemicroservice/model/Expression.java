package com.metrix.activitypipelinemicroservice.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Expression")
public class Expression {

    @Indexed(unique = true)
    @ApiModelProperty(notes = "Criteria Id")
    private String expressionId;
    @ApiModelProperty(notes = "Rule Id")
    private String activityRuleId;
    @ApiModelProperty(notes = "Expression Category")
    private String lhs;
    @ApiModelProperty(notes = "Expression Operator")
    private String operator;
    @ApiModelProperty(notes = "Expression Value")
    private String rhs;


    public Expression() {
    }

    public Expression(String expressionId, String activityRuleId, String lhs, String operator, String rhs) {
        this.expressionId = expressionId;
        this.activityRuleId = activityRuleId;
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    public String getExpressionId() {
        return expressionId;
    }

    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }

    public String getActivityRuleId() {
        return activityRuleId;
    }

    public void setActivityRuleId(String activityRuleId) {
        this.activityRuleId = activityRuleId;
    }

    public String getLhs() {
        return lhs;
    }

    public void setLhs(String lhs) {
        this.lhs = lhs;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRhs() {
        return rhs;
    }

    public void setRhs(String rhs) {
        this.rhs = rhs;
    }
}
