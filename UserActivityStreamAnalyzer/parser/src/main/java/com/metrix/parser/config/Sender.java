package com.metrix.parser.config;


import com.metrix.libs.model.ActivityStreamData;
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
    private KafkaTemplate<String, ActivityStreamData> kafkaTemplate;

    @Value("${app.topic.producer}")
    private String topic;

    public void send(ActivityStreamData activityStreamData){
//        System.out.println(kafkaTemplate);
        LOG.info("sending message='{}' to topic='{}'", activityStreamData, topic);
        kafkaTemplate.send(topic, activityStreamData);
    }
}
