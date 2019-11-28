package com.metrix.activitypipelinemicroservice.repository;

import com.metrix.activitypipelinemicroservice.model.ActivityPipeline;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface IActivityPipelineRepository extends MongoRepository<ActivityPipeline, String> {
    ActivityPipeline findByActivityPipelineId(String id);

    ArrayList<ActivityPipeline> findByIssuerProfileId(String issuerProfileId);
}
