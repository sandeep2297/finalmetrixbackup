package com.metrix.webhook.controller;

import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.libs.model.ResultSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.ZoneId;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class WebsocketController {
    public static Logger logger = LogManager.getLogger(WebsocketController.class);
    private final SimpMessagingTemplate template;
    @Autowired
    ResultSetController websocketTrigger;

    @Autowired
    WebsocketController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void onReceivedMesage(@DestinationVariable String pipelineId, @DestinationVariable String issuerId, @DestinationVariable ActivityRuleEvaluationResults results) {
        ArrayList<ResultSet> resultSetArrayList = new ArrayList<>();
         ResultSet resultSet = new ResultSet(results.getActivityTime().toString(), results.getActivityRuleName(),
                results.getEvaluationStatus().toString(), results.getActorId(),
                results.getUserData().getObjectContentType(),
                Long.toString(
                        results.getEvalEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() -
                                results.getEvalStartTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()),
                results.getActivityExpressionResult(), results.getExpressions());
        resultSetArrayList.add(resultSet);
//        resultSetArrayList = websocketTrigger.getResults(issuerId,pipelineId);
        logger.info("WebSocket Triggered Successfully");
        logger.info(resultSetArrayList);
        this.template.convertAndSend("/chat/" + issuerId + "/" + pipelineId , resultSetArrayList);
    }
}