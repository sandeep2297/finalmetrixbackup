package com.metrix.awardsmicroservice.wwwservice.controller;

import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.exception.IssuerNotFoundException;
import com.metrix.awardsmicroservice.libs.model.Assertion;
import com.metrix.awardsmicroservice.libs.model.Issuer;
import com.metrix.awardsmicroservice.libs.model.IssuerProfile;
import com.metrix.awardsmicroservice.libs.service.IssuerServiceImpl;
import com.metrix.awardsmicroservice.wwwservice.transformer.ReceiveIssuerProfile;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1")
public class IssuerController {

    @Autowired
    IssuerServiceImpl issuerService;

    @Autowired
    ReceiveIssuerProfile receiveIssuerProfile;

    private static Logger logger = LogManager.getLogger(BadgeController.class);

    @GetMapping("issuer/{clientid}")
    public ArrayList<Issuer> getByClientId(@PathVariable("clientid") String clientId) throws IssuerNotFoundException {
        ArrayList<Issuer> issuerArrayList =  receiveIssuerProfile.saveIssuerList(clientId);
        receiveIssuerProfile.saveIssuer();
        return issuerArrayList;
    }

    public Issuer updateRevocationListByIssuerId(String issuerId, String assertionId) {
        return issuerService.updateRevocationListByIssuerId(issuerId, assertionId);
    }

    @ApiOperation("Assertions List Which are Revoked By Issuer")
    @GetMapping("/issuer/{issuerId}/revokedassertions")
    public ArrayList<Assertion> getAllRevokedAssertionByIssuerId(String issuerId) {

        return issuerService.getAllRevokedAssertionByIssuerId(issuerId);
    }
}