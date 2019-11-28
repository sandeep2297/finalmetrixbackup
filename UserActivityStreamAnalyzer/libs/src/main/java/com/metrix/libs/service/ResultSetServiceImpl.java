package com.metrix.libs.service;

import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.libs.model.DurationResult;
import com.metrix.libs.model.GraphData;
import com.metrix.libs.model.ResultSet;
import com.metrix.libs.repository.ResultRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class ResultSetServiceImpl implements IResutSetService {
    @Autowired
    ResultRepository repository;


    private static Logger logger = LogManager.getLogger(ResultSetServiceImpl.class);

    public ArrayList<ResultSet> findByPipelineIdAndIssuerId(String activityPipelineId, String issuerProfileId) {
        ArrayList<ActivityRuleEvaluationResults> activityRuleEvaluationResults = repository.findByActivityPipelineIdAndIssuerProfileId(activityPipelineId, issuerProfileId);
        logger.info("IssuerID: " + issuerProfileId + " PipelineID " + activityPipelineId);
        logger.info("Result data From database: " + activityRuleEvaluationResults);
        ArrayList<ResultSet> resultSetArrayList = new ArrayList<>();
        ResultSet resultSet;
        ActivityRuleEvaluationResults results;
        for (int i = 0; i < activityRuleEvaluationResults.size(); i++) {
            results = activityRuleEvaluationResults.get(i);
            resultSet = new ResultSet(results.getActivityTime().toString(), results.getActivityRuleName(),
                    results.getEvaluationStatus().toString(), results.getActorId(),
                    results.getUserData().getObjectContentType(),
                    Long.toString(
                            results.getEvalEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() -
                                    results.getEvalStartTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()),
                    results.getActivityExpressionResult(), results.getExpressions());
            resultSetArrayList.add(resultSet);
        }
        Collections.reverse(resultSetArrayList);
        return resultSetArrayList;
    }

//    public GraphData findByIssuerId(String issuerId) {
//        ArrayList<ActivityRuleEvaluationResults> activityRuleEvaluationResults = repository.findByIssuerProfileId(issuerId);
//        GraphData graphData = new GraphData();
//        graphData.setIssuerProfileId(issuerId);
//        graphData.setTotalcount(activityRuleEvaluationResults.size());
//
//        return graphData;
//
//    }

//    @Override
//    public ArrayList<DurationResult> findByDurationResult(String issuerid) {
//        LocalDateTime endTime = LocalDateTime.now();
//        LocalDateTime startTime = null;
//        ArrayList<DurationResult> durationResultArrayList = new ArrayList<>();
//        int count=0;
//        DurationResult durationResult;
//        for(int i=1;i<=24;i++) {
//            startTime = endTime.minusHours(1);
//            count = 0;
//            ArrayList<ActivityRuleEvaluationResults> activityRuleEvaluationResults = repository.findByIssuerProfileIdAndCreatedOnBetween(issuerid, startTime, endTime);
//            for (ActivityRuleEvaluationResults item : activityRuleEvaluationResults) {
//                String result = String.valueOf(item.getEvaluationStatus());
//                if (result.equals("PASSED")) {
//                    count++;
//                }
//            }
//            durationResult = new DurationResult(activityRuleEvaluationResults.size(),count,activityRuleEvaluationResults.size()-count,issuerid,startTime);
//            durationResultArrayList.add(durationResult);
//            endTime = startTime;
//        }
//        logger.info(durationResultArrayList);
//        return durationResultArrayList;
//    }

    //    @Override
//    public ArrayList<DurationResult> findByAnyDurationResult(String issuerid, LocalDateTime startTime, LocalDateTime endTime) {
////        ArrayList<ActivityRuleEvaluationResults> activityRuleEvaluationResults = repository.findByIssuerProfileIdAndCreatedOnBetween(issuerid, startDate, endDate);
//
//        ArrayList<DurationResult> durationResultArrayList = new ArrayList<>();
//        int count;
//        DurationResult durationResult;
//        for(int i=1;i<=24;i++) {
//            endTime = startTime.plusHours(1);
//            count = 0;
//            ArrayList<ActivityRuleEvaluationResults> activityRuleEvaluationResults = repository.findByIssuerProfileIdAndCreatedOnBetween(issuerid, startTime, endTime);
//            for (ActivityRuleEvaluationResults item : activityRuleEvaluationResults) {
//                String result = String.valueOf(item.getEvaluationStatus());
//                if (result.equals("PASSED")) {
//                    count++;
//                }
//            }
//            durationResult = new DurationResult(activityRuleEvaluationResults.size(),count,activityRuleEvaluationResults.size()-count,issuerid,startTime);
//            durationResultArrayList.add(durationResult);
//            startTime = endTime;
//        }
//        logger.info(durationResultArrayList);
//        return durationResultArrayList;
////        return activityRuleEvaluationResults;
//    }
    public GraphData findByIssuerId(String issuerId) {
        ArrayList<ActivityRuleEvaluationResults> activityRuleEvaluationResults = repository.findByIssuerProfileId(issuerId);
        int count = 0;
        GraphData graphData = new GraphData();
        graphData.setIssuerProfileId(issuerId);
        for (ActivityRuleEvaluationResults item : activityRuleEvaluationResults) {
            String result = String.valueOf(item.getEvaluationStatus());
            if (result.equals("PASSED")) {
                count++;
            }
        }
        graphData.setTotalcount(activityRuleEvaluationResults.size());
        graphData.setPasscount(count);
        graphData.setFailcount(activityRuleEvaluationResults.size() - count);


        return graphData;

    }
}
