package com.metrix.webhook.controller;

import com.metrix.libs.model.WebHookData;
import com.metrix.webhook.Filter;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/v1")
public class WebHookController {

    public static Logger logger = LogManager.getLogger(WebHookController.class);

    @Autowired
    Filter filter;

    @Autowired
    private KafkaTemplate<String, WebHookData> kafkaTemplate;

    @Value("${app.topic.producer}")
    private String topic;

    //Getting the real time data from different sources
    @SuppressWarnings("unchecked")
    @ApiOperation("Getting the real time data from the github through webhook")
    @PostMapping(path = "/webhook/issuerid/{issuerid}/pipelineid/{pipelineid}")
    public ResponseEntity receiveFromWebhook(@RequestBody HashMap<String, Object> map,
                                                     @RequestHeader HashMap<String, String> headers,
                                                     @PathVariable String issuerid, @PathVariable String pipelineid ) {
       // boolean result = parser.recievedData(issuerid,pipelineid, map, headers);
       // if (result) return ResponseEntity.ok("Succesful");
        WebHookData webHookData = new WebHookData();
        webHookData.setBody(map);
        webHookData.setHeader(headers);
        webHookData.setIssuerId(issuerid);
        webHookData.setPipelineId(pipelineid);
//        filter =
        if(filter.checkActive(issuerid,pipelineid)) {
            logger.info("sending message='{ " + webHookData + " }' to topic='{ " + topic + " }'");
            kafkaTemplate.send(topic, webHookData);
        }
        return ResponseEntity.ok(200);
    }



}