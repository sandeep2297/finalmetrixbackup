package com.metrix.activitypipelinemicroservice.controller;

import com.metrix.activitypipelinemicroservice.model.BadgeClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name = "AwardsMicroservice")
public interface IFeignClientAwards {

    @GetMapping("api/v1/badgeclass/issuerid/{issuerId}")
    ArrayList<BadgeClass> findBadgeClassByIssuerId(@PathVariable("issuerId") String issuerId);

}
