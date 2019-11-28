package com.metrix.webhook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.webhook.controller.WebsocketController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RedisMessageSubscriber implements MessageListener {
    ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageSubscriber.class);

    @Autowired
    WebsocketController websocketTrigger;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        ActivityRuleEvaluationResults results = null;
        try {
            LOGGER.info("Data incoming from redis::+ ", message.toString());
            results = objectMapper.readValue(message.toString(), ActivityRuleEvaluationResults.class);
            websocketTrigger.onReceivedMesage(results.getActivityPipelineId(), results.getIssuerProfileId(),results);
            LOGGER.info("WebSocket Triggered Successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("Received data - " + results+ " from Topic - " + new String(pattern));
    }
}
