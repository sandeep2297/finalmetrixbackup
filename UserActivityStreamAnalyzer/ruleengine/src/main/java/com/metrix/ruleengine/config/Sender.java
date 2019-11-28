package com.metrix.ruleengine.config;


import com.metrix.libs.model.ActivityRuleEvaluationResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, ActivityRuleEvaluationResults> kafkaTemplate;

    @Value("${app.topic.producer}")
    private String topic;

    public void send(ActivityRuleEvaluationResults activityRuleEvaluationResults){
//        System.out.println(kafkaTemplate);
        LOG.info("sending message='{}' to topic='{}'", activityRuleEvaluationResults, topic);
        kafkaTemplate.send(topic, activityRuleEvaluationResults);
    }
}
