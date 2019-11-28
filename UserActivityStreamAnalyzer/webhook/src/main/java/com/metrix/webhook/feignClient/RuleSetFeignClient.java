package com.metrix.webhook.feignClient;


import com.metrix.libs.model.ActivityPipeline;
import com.metrix.libs.model.ActivityRule;
import com.metrix.libs.model.Expression;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Repository
@FeignClient(name = "ActivityPipelineMicroservice")
public interface RuleSetFeignClient {
    @GetMapping("/api/v1/issuer/{issuerProfileId}/pipeline/{pipelineId}/rule")
    ArrayList<ActivityRule> getRule(@PathVariable("pipelineId") String pipelineId, @PathVariable("issuerProfileId") String issuerProfileId);

    @GetMapping("/api/v1/expression/{ruleid}")
    ArrayList<Expression> getExpression(@PathVariable("ruleid") String ruleid);

    @GetMapping("/api/v1/issuer/{issuerProfileId}/pipeline/{pipelineId}")
    ActivityPipeline getPipelineDetails(@PathVariable("issuerProfileId") String issuerProfileId, @PathVariable("pipelineId") String pipelineId);
}
