package com.metrix.activitypipelinemicroservice.service;

import com.metrix.activitypipelinemicroservice.exception.DataNotFoundException;
import com.metrix.activitypipelinemicroservice.model.ActivityPipeline;

import java.util.ArrayList;

public interface IActivityPipelineService {
    ActivityPipeline addPipeline(ActivityPipeline pipeline);

    ArrayList<ActivityPipeline> getPipelineByIssuerId(String issuerProfileId) throws DataNotFoundException;

    ActivityPipeline getActivityPipelineById(String id);

    ActivityPipeline updateActivityPipelineStatus(String pipelineId, String response);
}
