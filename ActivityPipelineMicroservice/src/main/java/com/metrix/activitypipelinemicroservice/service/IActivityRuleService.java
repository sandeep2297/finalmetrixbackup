package com.metrix.activitypipelinemicroservice.service;

import com.metrix.activitypipelinemicroservice.exception.DataNotFoundException;
import com.metrix.activitypipelinemicroservice.model.ActivityRule;

import java.util.ArrayList;
import java.util.HashMap;

public interface IActivityRuleService {
    ActivityRule addActivityRule(String issuerProfileId, String pipelineId, HashMap<String, Object> map);

    ActivityRule findActivityRuleById(String activityRuleId) throws DataNotFoundException;

    ArrayList<ActivityRule> findActivityPipelineById(String activityPipelineId);

    ActivityRule patchArchieveStatusOfRule(String issuerProfileId, String pipelineId, String ruleId, boolean archiveStatus);
}
