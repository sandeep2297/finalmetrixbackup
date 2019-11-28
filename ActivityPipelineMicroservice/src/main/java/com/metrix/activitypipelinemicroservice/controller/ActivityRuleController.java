package com.metrix.activitypipelinemicroservice.controller;

import com.metrix.activitypipelinemicroservice.exception.DataNotFoundException;
import com.metrix.activitypipelinemicroservice.model.ActivityRule;
import com.metrix.activitypipelinemicroservice.service.ActivityRuleServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1")
public class ActivityRuleController {

    @Autowired
    ActivityRuleServiceImpl activityRuleServiceImpl;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Post mapping to save activity rule into database by issuer
    @ApiOperation("Posting a Activity Rule")
    @PostMapping("/issuer/{issuerProfileId}/pipeline/{pipelineId}/rule")
    public ResponseEntity<ActivityRule> createIssuer(@PathVariable("issuerProfileId") String issuerProfileId, @PathVariable("pipelineId") String pipelineId, @RequestBody HashMap<String, Object> map) {
        try {
            return ResponseEntity.ok(activityRuleServiceImpl.addActivityRule(issuerProfileId, pipelineId, map));
        } catch (DuplicateKeyException duplicatekey) {
            logger.error("In rule controller =>" + LocalDateTime.now() + " " + duplicatekey.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            logger.error("In rule controller =>" + LocalDateTime.now() + " " + exception.getMessage());
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }
    }

    // Get mapping for fetching rules by rule engine using pipeline Id
    @ApiOperation("Getting Activity rules by pipeline Id")
    @GetMapping("/issuer/{issuerProfileId}/pipeline/{pipelineId}/rule")
    public ArrayList<ActivityRule> getRule(@PathVariable("pipelineId") String pipelineId, @PathVariable("issuerProfileId") String issuerProfileId) {
        ArrayList<ActivityRule> activityRuleArrayList = activityRuleServiceImpl.findActivityPipelineById(pipelineId);
        return activityRuleArrayList;
    }

    // Get activity rule by Id
    @ApiOperation("Getting Activity rules by rule Id")
    @GetMapping("/issuer/{issuerProfileId}/pipeline/{pipelineId}/rule/{ruleId}")
    public ActivityRule getRuleById(@PathVariable("ruleId") String ruleId, @PathVariable("issuerProfileId") String issuerProfileId, @PathVariable("pipelineId") String pipelineId) {
        try {
            return activityRuleServiceImpl.findActivityRuleById(ruleId);
        } catch (DataNotFoundException dataNotFound) {
            logger.error("In get rule by rule Id => " + LocalDateTime.now() + " " + dataNotFound.getMessage());
            return null;
        } catch (Exception exception) {
            logger.error("In get rule by rule Id => " + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }

    // Archive a particular rule, patch archieve of rule
    @PatchMapping("/issuer/{issuerProfileId}/pipeline/{pipelineId}/rule/{ruleId}")
    public ActivityRule archiveRule(@PathVariable("issuerProfileId") String issuerProfileId, @PathVariable("pipelineId") String pipelineId, @PathVariable("ruleId") String ruleId, @RequestBody boolean archiveStatus) {
        try {
            return activityRuleServiceImpl.patchArchieveStatusOfRule(issuerProfileId, pipelineId, ruleId, archiveStatus);
        } catch (Exception exception) {
            logger.error("In get rule by rule Id => " + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }
}
