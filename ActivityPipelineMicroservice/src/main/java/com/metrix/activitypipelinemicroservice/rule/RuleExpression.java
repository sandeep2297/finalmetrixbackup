package com.metrix.activitypipelinemicroservice.rule;

public class RuleExpression {
    String conjunction;
    String type;
    String operator;
    String expressionValue;

    public RuleExpression() {
    }

    public RuleExpression(String conjunction, String type, String operator, String expressionValue) {
        this.conjunction = conjunction;
        this.type = type;
        this.operator = operator;
        this.expressionValue = expressionValue;
    }

    public String getConjunction() {
        return conjunction;
    }

    public void setConjunction(String conjunction) {
        this.conjunction = conjunction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(String expressionValue) {
        this.expressionValue = expressionValue;
    }

    @Override
    public String toString() {
        return "RuleExpression{" +
                "conjunction='" + conjunction + '\'' +
                ", type='" + type + '\'' +
                ", operator='" + operator + '\'' +
                ", expressionValue='" + expressionValue + '\'' +
                '}';
    }
}
