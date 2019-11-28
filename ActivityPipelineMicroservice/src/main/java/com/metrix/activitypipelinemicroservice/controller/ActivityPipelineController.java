package com.metrix.activitypipelinemicroservice.controller;

import com.metrix.activitypipelinemicroservice.exception.DataNotFoundException;
import com.metrix.activitypipelinemicroservice.model.ActivityPipeline;
import com.metrix.activitypipelinemicroservice.model.PipelineStatus;
import com.metrix.activitypipelinemicroservice.service.ActivityPipelineServiceImpl;
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
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class ActivityPipelineController {

    @Autowired
    ActivityPipelineServiceImpl activityPipelineServiceImpl;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Pipeline contains information about application pipeline,
     * An issuer can have more than one pipeline to manage multiple
     * applications
     */
    @ApiOperation("Creating an activity pipeline")
    @PostMapping("/issuer/{issuerProfileId}/pipeline")
    @ResponseBody
    public ResponseEntity<ActivityPipeline> createIssuer(@PathVariable("issuerProfileId") String issuerProfileId, @RequestBody ActivityPipeline pipeline) {
        pipeline.setIssuerProfileId(issuerProfileId);
        pipeline.setCreatedOn(LocalDateTime.now());
        pipeline.setCreatedBy("OWNER");
        pipeline.setPipelineStatus(PipelineStatus.DRAFTED);
        pipeline.setUpdatedOn(LocalDateTime.now());
        pipeline.setUpdatedBy("OWNER");
        pipeline.setActivityPipelineId(UUID.randomUUID().toString());
        String modifiedEndPoint = pipeline.getEndpointUrl() + pipeline.getActivityPipelineId();
        pipeline.setEndpointUrl(modifiedEndPoint);
        try {
            return ResponseEntity.ok(activityPipelineServiceImpl.addPipeline(pipeline));
        } catch (DuplicateKeyException duplicateKey) {
            logger.error("activity pipeline post method =>" + LocalDateTime.now() + " " + duplicateKey.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
            logger.error("activity pipeline post method =>" + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }

    /**
     * To return all the available pipeline for a particular
     * issuer
     */
    @GetMapping("/issuer/{issuerProfileId}/pipeline")
    public ArrayList<ActivityPipeline> getPipelineByIssuerId(@PathVariable("issuerProfileId") String issuerProfileId) {

        try {
            return activityPipelineServiceImpl.getPipelineByIssuerId(issuerProfileId);
        } catch (DataNotFoundException dataNotFound) {
            logger.error("In activity pipeline, fetch pipeline by issuer Id" + LocalDateTime.now() + " " + dataNotFound.getMessage());
            return null;
        } catch (Exception exception) {
            logger.error("In activity pipeline, fetch pipeline by issuer Id" + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }

    /**
     * To return a specific pipeline Details by pipeline Id
     */
    @GetMapping("/issuer/{issuerProfileId}/pipeline/{pipelineId}")
    public ActivityPipeline getPipelineDetails(@PathVariable("issuerProfileId") String issuerProfileId, @PathVariable("pipelineId") String pipelineId) {
        try {
            return activityPipelineServiceImpl.getActivityPipelineById(pipelineId);
        } catch (Exception exception) {
            logger.error("In activity pipeline, fetch pipeline by pipeline Id" + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }

    /**
     * Patch mapping to update status of pipeline
     */
    @PatchMapping("/issuer/{issuerProfileId}/pipeline/{pipelineId}")
    public ActivityPipeline updateStatusOfPipeline(@PathVariable("issuerProfileId") String issuerProfileId, @PathVariable("pipelineId") String pipelineId, @RequestBody String response) {
        try {
            return activityPipelineServiceImpl.updateActivityPipelineStatus(pipelineId, response);
        } catch (Exception exception) {
            logger.error("In activity pipeline, fetch pipeline by pipeline Id" + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }

}
