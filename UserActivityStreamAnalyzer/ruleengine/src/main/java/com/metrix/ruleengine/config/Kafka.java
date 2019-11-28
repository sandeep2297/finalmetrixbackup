package com.metrix.ruleengine.config;

import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.libs.model.RuleActivityData;
import com.metrix.ruleengine.ruleInitiator.RulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Kafka {

    @Autowired
    Sender sender;

    @KafkaListener(topics = "${app.topic.consumer}")
    public void kafkalistener(@Payload RuleActivityData ruleActivityData){
        RulesEngine rulesEngine = new RulesEngine();
        ActivityRuleEvaluationResults entityResult = rulesEngine.init(ruleActivityData);
        entityResult.setExpressions(ruleActivityData.getExpressionArrayList());
        entityResult.setEvalEndTime(LocalDateTime.now());
        boolean check = false;
        for(int i=0;i<entityResult.getActivityExpressionResult().size();i++){
            if(entityResult.getActivityExpressionResult().get(i).getExpressionResult()){
                check = true;
            }
        }
        if(check) {
            sender.send(entityResult);
        }
    }
}
