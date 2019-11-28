package com.metrix.libs.model;

import java.util.List;

public class ResultSet {
    String activitytime;
    String rulename;
    String result;
    String actor;
    String object;
    String duration;
    List<ActivityExpressionResult> evaluation_result;
    List<Expression> expressions;

    public ResultSet() {
    }

    public ResultSet(String activitytime, String rulename, String result, String actor, String object, String duration,
                     List<ActivityExpressionResult> evaluation_result, List<Expression> expressions) {
        this.activitytime = activitytime;
        this.rulename = rulename;
        this.result = result;
        this.actor = actor;
        this.object = object;
        this.duration = duration;
        this.evaluation_result = evaluation_result;
        this.expressions = expressions;
    }

    public String getActivitytime() {
        return activitytime;
    }

    public void setActivitytime(String activitytime) {
        this.activitytime = activitytime;
    }

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<ActivityExpressionResult> getEvaluation_result() {
        return evaluation_result;
    }

    public void setEvaluation_result(List<ActivityExpressionResult> evaluation_result) {
        this.evaluation_result = evaluation_result;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        return "ResultSet{" +
                "activitytime='" + activitytime + '\'' +
                ", rulename='" + rulename + '\'' +
                ", result='" + result + '\'' +
                ", actor='" + actor + '\'' +
                ", object='" + object + '\'' +
                ", duration='" + duration + '\'' +
                ", evaluation_result=" + evaluation_result +
                ", expressions=" + expressions +
                '}';
    }
}
