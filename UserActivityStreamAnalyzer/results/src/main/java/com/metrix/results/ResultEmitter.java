package com.metrix.results;

import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.libs.service.ResultService;
import com.metrix.results.component.UserActivityResultsPublisher;
import com.metrix.results.config.RedisMessagePublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ResultEmitter {
    public static Logger logger = LogManager.getLogger(ResultEmitter.class);

    @Autowired
    ResultService resultService;

    @Autowired
    UserActivityResultsPublisher userActivityResultsPublisher;

    @Autowired
    private RedisMessagePublisher publisher;

    @KafkaListener(topics = "${app.topic.consumer}")
    public void saveToDatabase(@Payload ActivityRuleEvaluationResults results){
        try {
            logger.info("Before saving data to database: " + results);
            logger.info("Awards given for this rule :- "+ results.getAwards());
            resultService.saveData(results);
            publisher.publish(results);
            userActivityResultsPublisher.produceMsg(results);
        } catch (DuplicateKeyException duplicate) {
            logger.error("Duplicate data is entered");
        }

    }
}
