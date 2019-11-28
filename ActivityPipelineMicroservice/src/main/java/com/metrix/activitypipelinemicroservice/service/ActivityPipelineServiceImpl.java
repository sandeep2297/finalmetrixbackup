package com.metrix.activitypipelinemicroservice.service;

import com.metrix.activitypipelinemicroservice.exception.DataNotFoundException;
import com.metrix.activitypipelinemicroservice.model.ActivityPipeline;
import com.metrix.activitypipelinemicroservice.model.PipelineStatus;
import com.metrix.activitypipelinemicroservice.repository.IActivityPipelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ActivityPipelineServiceImpl implements IActivityPipelineService {

    @Autowired
    IActivityPipelineRepository activityPipelineRepo;

    // Service to save pipeline data to database
    public ActivityPipeline addPipeline(ActivityPipeline pipeline) throws DuplicateKeyException {
        if (activityPipelineRepo.findByActivityPipelineId(pipeline.getActivityPipelineId()) == null)
            return activityPipelineRepo.save(pipeline);
        else {
            throw new DuplicateKeyException("Pipeline already exists");
        }
    }

    // Service to fetch activity pipeline details using activity pipeline id
    public ActivityPipeline getActivityPipelineById(String id) {
        return activityPipelineRepo.findByActivityPipelineId(id);
    }

    // To get pipeline for a particular issuer
    public ArrayList<ActivityPipeline> getPipelineByIssuerId(String issuerProfileId) throws DataNotFoundException {
        if (activityPipelineRepo.findByIssuerProfileId(issuerProfileId) != null) {
            return activityPipelineRepo.findByIssuerProfileId(issuerProfileId);
        } else {
            throw new DataNotFoundException("pipeline settings cannot be found for issuer Id" + issuerProfileId);
        }
    }

    // To patch pipeline for a particular pipeline
    public ActivityPipeline updateActivityPipelineStatus(String pipelineId, String response) {
        ActivityPipeline activityPipeline = activityPipelineRepo.findByActivityPipelineId(pipelineId);
//        activityPipeline.setActivityPipelineId(pipelineId);
        if (response.equals("ACTIVE")) {
            activityPipeline.setPipelineStatus(PipelineStatus.ACTIVE);
        }
        if (response.equals("SUSPENDED")) {
            activityPipeline.setPipelineStatus(PipelineStatus.SUSPENDED);
        }
        return activityPipelineRepo.save(activityPipeline);
    }
}
