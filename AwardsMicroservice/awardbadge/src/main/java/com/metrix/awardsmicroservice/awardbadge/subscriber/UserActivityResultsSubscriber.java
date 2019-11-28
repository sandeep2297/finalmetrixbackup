package com.metrix.awardsmicroservice.awardbadge.subscriber;

import com.metrix.awardsmicroservice.awardbadge.controller.EvaluationService;
import com.metrix.awardsmicroservice.libs.exception.BadgeClassNotFoundException;
import com.metrix.awardsmicroservice.libs.model.ActivityRuleEvaluationResults;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserActivityResultsSubscriber {

    @Autowired
    EvaluationService evaluationService;


    @RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void receivedMessage(ActivityRuleEvaluationResults evaluationResults) throws BadgeClassNotFoundException {
        evaluationService.saveResults(evaluationResults);
    }

}