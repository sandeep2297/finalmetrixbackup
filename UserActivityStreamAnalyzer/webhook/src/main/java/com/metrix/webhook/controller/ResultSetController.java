package com.metrix.webhook.controller;


import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.libs.model.DurationResult;
import com.metrix.libs.model.GraphData;
import com.metrix.libs.model.ResultSet;
import com.metrix.libs.service.ResultSetServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1")
public class ResultSetController {

    @Autowired
    ResultSetServiceImpl resultSetService;

    public static Logger logger = LogManager.getLogger(WebHookController.class);

    @GetMapping(path = "/resultset/issuer/{issuerid}/pipeline/{pipelineid}")
    public ArrayList<ResultSet> getResults(@PathVariable String issuerid, @PathVariable String pipelineid){
        return resultSetService.findByPipelineIdAndIssuerId(pipelineid,issuerid);
    }

    @GetMapping(path = "/issuer/{issuerid}/resultdata")
    public GraphData getGraphData(@PathVariable String issuerid){
        return resultSetService.findByIssuerId(issuerid);
    }


    @GetMapping(path = "/test")
    public String testing(){
        return "WebhookSideCar";
    }

//    @GetMapping(path = "/issuer/{issuerid}/durationresult")
//    public ArrayList<DurationResult> getDuration(@PathVariable String issuerid){
//        return resultSetService.findByDurationResult(issuerid);
//    }
//
//    @GetMapping(path ="/issuer/{issuerid}/evalresult")
//    public ArrayList<DurationResult> getAnyDuration(@PathVariable String issuerid,@RequestParam String startDate, @RequestParam String endDate){
//        LocalDateTime startDateTime = LocalDateTime.parse(startDate);
//        LocalDateTime endDateTime = LocalDateTime.parse(endDate);
//        return resultSetService.findByAnyDurationResult(issuerid,startDateTime,endDateTime);
//    }

}
