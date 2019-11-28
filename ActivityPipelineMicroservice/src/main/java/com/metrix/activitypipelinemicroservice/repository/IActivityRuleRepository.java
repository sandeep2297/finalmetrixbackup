package com.metrix.activitypipelinemicroservice.repository;

import com.metrix.activitypipelinemicroservice.model.ActivityRule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface IActivityRuleRepository extends MongoRepository<ActivityRule, String> {
    ActivityRule findByActivityRuleId(String activityRuleId);

    ArrayList<ActivityRule> findByActivityPipelineId(String activityPipelineId);
}
