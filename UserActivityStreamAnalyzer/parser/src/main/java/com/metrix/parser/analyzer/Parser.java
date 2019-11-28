package com.metrix.parser.analyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metrix.libs.model.*;
import com.metrix.parser.config.Sender;
import com.metrix.parser.feignclient.RuleSetFeignClient;
import com.metrix.parser.transformer.GitLabTransformer;
import com.metrix.parser.transformer.GithubTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.HashMap;



@Component
public class Parser {

    public static Logger logger = LogManager.getLogger(Parser.class);
    ActivityRuleEvaluationResults ruleEvaluationResults;
    String event;
    @Autowired
    RuleSetFeignClient proxy;
    @Autowired
    GithubTransformer githubTransformer;
    @Autowired
    GitLabTransformer gitLabTransformer;
    @Autowired
    Sender sender;
    @Autowired

    ObjectMapper mapper;
    private UserData userData;

    @KafkaListener(topics = "${app.topic.consumer}")
    public boolean recievedData(@Payload WebHookData webHookData) {
        System.out.println("Came to Parser");
        HashMap<String, Object> map = webHookData.getBody();
        HashMap<String, String> headers = webHookData.getHeader();
        String issuerid = webHookData.getIssuerId();
        String pipelineid = webHookData.getPipelineId();
        LocalDateTime activityTime = LocalDateTime.now();
        mapper = new ObjectMapper();
        ActivityPipeline transformer = transformerCheck(issuerid,pipelineid);
        try {
            logger.info("printing the value");
            logger.info(transformer);
            if (!(transformer.getTransformer()).equals("") ) {
                logger.info("Transformer is nedded");
                if ("github".equals(transformer.getTransformer().toLowerCase())) {
                    event = headers.get("x-github-event").toString();
                    if (event.equals("push") || event.equals("create") || event.equals("issues")) {
                        userData = githubTransformer.convertToActivityStream(map, headers);
                        logger.info("UserData in AS format : " + userData);
                        ActivityStreamData activityStreamData = new ActivityStreamData(userData,issuerid,pipelineid,activityTime);
                        sender.send(activityStreamData);
                        return true;
                    }
                } else if ("gitlab".equals(transformer.getTransformer().toLowerCase())) {
                    event = headers.get("x-gitlab-event").toString();
                    if (event.equals("Push Hook") || event.equals("Issue Hook")) {
                        userData = gitLabTransformer.convertToActivityStream(map, headers);
                        logger.info("UserData in AS format : " + userData);
                        ActivityStreamData activityStreamData = new ActivityStreamData(userData,issuerid,pipelineid,activityTime);
                        sender.send(activityStreamData);
                        return true;
                    }
                }
            }
            else {
                logger.debug("Check data in side activity stream");
                logger.debug("data from the webhook"+ webHookData.getBody());
                userData =  mapper.convertValue(webHookData.getBody(), UserData.class);
                logger.info("UserData in AS format : " + userData);
                ActivityStreamData activityStreamData = new ActivityStreamData(userData,issuerid,pipelineid,activityTime);
                sender.send(activityStreamData);

            }
        } catch (NullPointerException e){
            logger.error("The Pipeline id: "+ pipelineid + " trying to fetch is not there in database.");
        }

        return false;
    }

    private ActivityPipeline transformerCheck(String issuerid, String pipelineid) {
        return proxy.getPipelineDetails(issuerid,pipelineid);
    }
}
