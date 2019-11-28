package com.metrix.libs.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "useractivity")
public class ActivityRuleEvaluationResults {
    @Indexed(unique = true)
    private String evaluationId;
    private String activityPipelineId;
    private String issuerProfileId;
    private String actorId;
    private String activityRuleId;
    private String activityRuleName;
    private EvaluationStatus evaluationStatus;
    private List<ActivityExpressionResult> activityExpressionResult;
    private List<Expression> expressions;
    private List<Award> awards;
    private UserData userData;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime activityTime;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdOn;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime evalStartTime;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime evalEndTime;

    public ActivityRuleEvaluationResults() {
    }

    public ActivityRuleEvaluationResults(String evaluationId, String activityPipelineId, String issuerProfileId,
                                         String actorId, String activityRuleId, EvaluationStatus evaluationStatus,
                                         List<ActivityExpressionResult> activityExpressionResult, List<Award> awards,
                                         UserData userData, LocalDateTime activityTime, String createdBy,
                                         LocalDateTime createdOn, LocalDateTime evalStartTime,
                                         LocalDateTime evalEndTime) {
        this.evaluationId = evaluationId;
        this.activityPipelineId = activityPipelineId;
        this.issuerProfileId = issuerProfileId;
        this.actorId = actorId;
        this.activityRuleId = activityRuleId;
        this.evaluationStatus = evaluationStatus;
        this.activityExpressionResult = activityExpressionResult;
        this.awards = awards;
        this.userData = userData;
        this.activityTime = activityTime;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.evalStartTime = evalStartTime;
        this.evalEndTime = evalEndTime;
    }

    public ActivityRuleEvaluationResults(String evaluationId, String activityPipelineId, String issuerProfileId,
                                         String actorId, String activityRuleId, UserData userData,
                                         LocalDateTime activityTime, String createdBy, LocalDateTime createdOn,
                                         LocalDateTime evalStartTime) {
        this.evaluationId = evaluationId;
        this.activityPipelineId = activityPipelineId;
        this.issuerProfileId = issuerProfileId;
        this.actorId = actorId;
        this.activityRuleId = activityRuleId;
        this.userData = userData;
        this.activityTime = activityTime;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.evalStartTime = evalStartTime;
    }

    public String getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(String evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getActivityPipelineId() {
        return activityPipelineId;
    }

    public void setActivityPipelineId(String activityPipelineId) {
        this.activityPipelineId = activityPipelineId;
    }

    public String getIssuerProfileId() {
        return issuerProfileId;
    }

    public void setIssuerProfileId(String issuerProfileId) {
        this.issuerProfileId = issuerProfileId;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getActivityRuleId() {
        return activityRuleId;
    }

    public void setActivityRuleId(String activityRuleId) {
        this.activityRuleId = activityRuleId;
    }

    public EvaluationStatus getEvaluationStatus() {
        return evaluationStatus;
    }

    public void setEvaluationStatus(EvaluationStatus evaluationStatus) {
        this.evaluationStatus = evaluationStatus;
    }

    public List<ActivityExpressionResult> getActivityExpressionResult() {
        return activityExpressionResult;
    }

    public void setActivityExpressionResult(List<ActivityExpressionResult> activityExpressionResult) {
        this.activityExpressionResult = activityExpressionResult;
    }

    public List<Award> getAwards() {
        return awards;
    }

    public void setAwards(List<Award> awards) {
        this.awards = awards;
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

    public LocalDateTime getEvalStartTime() {
        return evalStartTime;
    }

    public void setEvalStartTime(LocalDateTime evalStartTime) {
        this.evalStartTime = evalStartTime;
    }

    public LocalDateTime getEvalEndTime() {
        return evalEndTime;
    }

    public void setEvalEndTime(LocalDateTime evalEndTime) {
        this.evalEndTime = evalEndTime;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityRuleName() {
        return activityRuleName;
    }

    public void setActivityRuleName(String activityRuleName) {
        this.activityRuleName = activityRuleName;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        return "ActivityRuleEvaluationResults{" +
                "evaluationId='" + evaluationId + '\'' +
                ", activityPipelineId=" + activityPipelineId +
                ", issuerProfileId='" + issuerProfileId + '\'' +
                ", actorId='" + actorId + '\'' +
                ", activityRuleId=" + activityRuleId +
                ", evaluationStatus=" + evaluationStatus +
                ", activityExpressionResult=" + activityExpressionResult +
                ", awards=" + awards +
                ", userData=" + userData +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", evalStartTime=" + evalStartTime +
                ", evalEndTime=" + evalEndTime +
                '}';
    }


}
