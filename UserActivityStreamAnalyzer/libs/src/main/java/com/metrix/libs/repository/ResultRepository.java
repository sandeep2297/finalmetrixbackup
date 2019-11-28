package com.metrix.libs.repository;
import com.metrix.libs.model.ActivityRuleEvaluationResults;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface ResultRepository extends MongoRepository<ActivityRuleEvaluationResults, String> {
    ArrayList<ActivityRuleEvaluationResults> findAll();
    ArrayList<ActivityRuleEvaluationResults> findByActivityPipelineIdAndIssuerProfileId(String activityPipelineId, String issuerProfileId);

    void deleteByEvaluationId(String evaluationId);

    ActivityRuleEvaluationResults findByEvaluationId(String evaluationId);
    ArrayList<ActivityRuleEvaluationResults> findByIssuerProfileId(String issuerProfileId);

    ArrayList<ActivityRuleEvaluationResults> findByIssuerProfileIdAndCreatedOnBetween(String issuerid, LocalDateTime startTime, LocalDateTime endTime);

    ArrayList<ActivityRuleEvaluationResults> findAllByCreatedOnBetween(LocalDateTime minusMinutes, LocalDateTime now);
}