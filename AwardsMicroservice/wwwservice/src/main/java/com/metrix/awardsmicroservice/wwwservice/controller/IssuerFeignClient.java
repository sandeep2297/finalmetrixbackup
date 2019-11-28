package com.metrix.awardsmicroservice.wwwservice.controller;

import com.metrix.awardsmicroservice.libs.model.IssuerProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@FeignClient(name = "ActivityPipelineMicroservice")
public interface IssuerFeignClient {
    @GetMapping("/api/v1/issuer/client/{clientid}")
    ArrayList<IssuerProfile> getByClientId(@PathVariable("clientid") String clientId);
}