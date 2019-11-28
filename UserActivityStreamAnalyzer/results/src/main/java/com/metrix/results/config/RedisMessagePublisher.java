package com.metrix.results.config;

import com.metrix.libs.model.ActivityRuleEvaluationResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessagePublisher.class);

    @Autowired
    private RedisTemplate<String, ActivityRuleEvaluationResults> redisTemplate;

    @Autowired
    private ChannelTopic topic;

    public void publish(ActivityRuleEvaluationResults message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
        LOGGER.info("Data - " + message + " sent through Redis Topic - " + topic.getTopic());

    }
}
