package com.metrix.libs.service;



import com.metrix.libs.model.ActivityRuleEvaluationResults;

import java.util.ArrayList;

public interface IResultService {
    ActivityRuleEvaluationResults saveData(ActivityRuleEvaluationResults ruleEvaluationData);

    ArrayList<ActivityRuleEvaluationResults> findResults();
}
