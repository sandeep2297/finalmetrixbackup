package com.metrix.activitypipelinemicroservice.controller;

import com.metrix.activitypipelinemicroservice.model.IssuerProfile;
import com.metrix.activitypipelinemicroservice.model.Status;
import com.metrix.activitypipelinemicroservice.service.IssuerProfileServiceImpl;
import com.mongodb.MongoException;
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
public class IssuerProfileController {

    @Autowired
    IssuerProfileServiceImpl issuerProfileServiceImpl;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Badges are issued by issuers, and every badge is
     * ssosciated with an issuer
     */
    @ApiOperation("Posting an Issuer Profile")
    @PostMapping("/issuer")
    @ResponseBody
    public ResponseEntity<IssuerProfile> createIssuer(@RequestBody IssuerProfile issuerProfile) {
        issuerProfile.setCreatedOn(LocalDateTime.now());
        issuerProfile.setCreatedBy("OWNER");
        issuerProfile.setUpdatedOn(LocalDateTime.now());
        issuerProfile.setUpdatedBy("OWNER");
        issuerProfile.setStatus(Status.ACTIVE);
        issuerProfile.setIssuerProfileId(UUID.randomUUID().toString());
        try {
            return ResponseEntity.ok(issuerProfileServiceImpl.addIssuer(issuerProfile));
        } catch (DuplicateKeyException duplicateKey) {
            logger.error("issuer profile post method =>" + LocalDateTime.now() + " " + duplicateKey.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (MongoException mongoException) {
            System.out.println(mongoException);
            logger.info("issuer profile post method =>" + LocalDateTime.now() + " " + mongoException.getMessage());
            return null;
        } catch (Exception exception) {
            logger.error("issuer profile post method =>" + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }

    /**
     * To select a particular issuer from all the
     * available issuers
     */
    @ApiOperation("Getting an Issuer Profile by Id")
    @GetMapping("/issuer/{issuerId}")
    @ResponseBody
    public IssuerProfile getByIssuerId(@PathVariable("issuerId") String issuerId) {
        try {
            return issuerProfileServiceImpl.findByIdOfIssuer(issuerId);
        } catch (Exception exception) {
            logger.error("issuer profile post method =>" + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }

    /**
     * To return all the issuers for a particular client
     */
    @ApiOperation("Getting an issuer profile by Client Id")
    @GetMapping("/issuer/client/{clientid}")
    @ResponseBody
    public ArrayList<IssuerProfile> getByClientId(@PathVariable("clientid") String clientId) {
        try {
            ArrayList<IssuerProfile> issuerList = issuerProfileServiceImpl.findByClientId(clientId);
            return issuerList;
        } catch (Exception exception) {
            logger.error("issuer profile post method =>" + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }


    @PatchMapping("/issuer/{issuerId}/update")
    public IssuerProfile updateIssuer(@PathVariable("issuerId") String issuerId, @RequestBody IssuerProfile issuerProfile){
        try{
            return issuerProfileServiceImpl.updateTheIssuer(issuerId);
        }
        catch(Exception exception){
            logger.error("In issuer to update the issuer => " + LocalDateTime.now() + " " + exception.getMessage());
            return null;
        }
    }
}
