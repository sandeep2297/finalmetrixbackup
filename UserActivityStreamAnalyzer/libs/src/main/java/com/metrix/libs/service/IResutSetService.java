package com.metrix.libs.service;

import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.libs.model.DurationResult;
import com.metrix.libs.model.GraphData;
import com.metrix.libs.model.ResultSet;


import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IResutSetService {
    ArrayList<ResultSet> findByPipelineIdAndIssuerId(String activityPipelineId, String issuerProfileId);
    GraphData findByIssuerId(String issuerId);
//    ArrayList<DurationResult> findByDurationResult(String issuerid);
//    ArrayList<DurationResult> findByAnyDurationResult(String issuerid,LocalDateTime startDate,LocalDateTime endDate);

}
