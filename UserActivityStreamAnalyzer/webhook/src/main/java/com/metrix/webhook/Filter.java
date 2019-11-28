package com.metrix.webhook;

import com.metrix.libs.model.ActivityPipeline;
import com.metrix.libs.model.PipelineStatus;
import com.metrix.webhook.feignClient.RuleSetFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Filter {
    @Autowired
    RuleSetFeignClient proxy;

    public boolean checkActive(String issuerId,String pipelineId){
        ActivityPipeline activityPipeline = proxy.getPipelineDetails(issuerId,pipelineId);
        if (activityPipeline.getPipelineStatus()== PipelineStatus.ACTIVE) return true;
        return false;
    }
}
