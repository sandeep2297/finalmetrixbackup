package com.metrix.rules;


import com.metrix.libs.model.*;
import com.metrix.rules.config.Sender;
import com.metrix.rules.feignclient.RuleSetFeignClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class RuleAndExpression {

    public static Logger logger = LogManager.getLogger(RuleAndExpression.class);

    @Autowired
    RuleSetFeignClient proxy;
    @Autowired
    Sender sender;
    @Autowired
    RuleNormalizer ruleNormalizer;

    @KafkaListener(topics = "${app.topic.consumer}")
    public void getRulesAndExpression(@Payload ActivityStreamData activityStreamData) {
        UserData userObject = activityStreamData.getUserData();
        String pipelineid = activityStreamData.getPipelineId();
        String issuerid = activityStreamData.getIssuerId();
        LocalDateTime activityTime = activityStreamData.getActivityTime();

        //Send the Java Object and Rules from database to rule engine
        ArrayList<ActivityRule> activityRuleArrayList = proxy.getRule(pipelineid, issuerid);
        logger.info("Rule from activity pipeline:- " + activityRuleArrayList);

        activityRuleArrayList = ruleNormalizer.checkRules(activityRuleArrayList);

        //Expression data ;
        ArrayList<Expression> expressionArrayList;
        for (ActivityRule activityRule : activityRuleArrayList) {
            expressionArrayList = proxy.getExpression(activityRule.getActivityRuleId());
            logger.info("Expressions of this rule from activity pipeline:- " + expressionArrayList);
            rulesinit(userObject, activityRule, expressionArrayList, activityTime, issuerid);
        }

    }

    public void rulesinit(UserData userObject, ActivityRule activityRule,
                                                   ArrayList<Expression> expressionArrayList,
                                                   LocalDateTime activityTime,String issuerid) {
        ActivityRuleEvaluationResults results = new ActivityRuleEvaluationResults(UUID.randomUUID().toString(),
                activityRule.getActivityPipelineId(), issuerid, userObject.getActorId(),
                activityRule.getActivityRuleId(),userObject, activityTime, "system", LocalDateTime.now(),
                LocalDateTime.now());
        results.setActivityRuleName(activityRule.getRuleName());
        RuleActivityData ruleActivityData = new RuleActivityData(activityRule, results, expressionArrayList);
        sender.send(ruleActivityData);
        results.setEvalEndTime(LocalDateTime.now());
        logger.info("Result after evaluation of Rule:- " + results);
    }
}
