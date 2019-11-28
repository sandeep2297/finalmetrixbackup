package com.metrix.awardsmicroservice.wwwservice.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.metrix.awardsmicroservice.libs.exception.AssertionNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.Assertion;
import com.metrix.awardsmicroservice.libs.model.Evidence;
import com.metrix.awardsmicroservice.libs.model.Recipient;
import com.metrix.awardsmicroservice.libs.model.Verification;
import com.metrix.awardsmicroservice.libs.service.AssertionServiceImpl;
import com.metrix.awardsmicroservice.libs.service.IssuerServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class AssertionsController {

    private static Logger logger = LogManager.getLogger(BadgeController.class);

    @Autowired
    AssertionServiceImpl assertionService;
    @Autowired
    IssuerServiceImpl issuerService;

    @ApiOperation("Assertion Information Saving")
    @PostMapping("/assertion")
    public ResponseEntity<Assertion> saveAssertion(@RequestBody Assertion assertion) {
        try {
////            System.out.println("assertion recipient"+assertion.getRecipient());
//            System.out.println("");
//            assertion.print();
            assertion.setAssertionId(UUID.randomUUID().toString());

            assertion.setIssuedOn(LocalDateTime.now());
            assertion.setType("assertion");
            assertion.setExpires(LocalDateTime.now());


            assertion.setAssertionNarrative(assertion.getAssertionNarrative());
            assertion.setRevoked(false);
            assertion.setRevocationReason("");
            Recipient recipient = new Recipient();
            recipient.setRecipientId(assertion.getRecipient().getRecipientId());
            recipient.setRecipientName(assertion.getRecipient().getRecipientName());
            recipient.setType("recipient");
            recipient.setHashed(false);
            recipient.setSalt("");

            assertion.setRecipient(recipient);

            Verification verification = new Verification();
            verification.setType("verification");

            assertion.setVerification(verification);
            return ResponseEntity.ok(assertionService.saveAssertion(assertion));
        } catch (DuplicateKeyException exception) {
            logger.info("Exception in posting assertion data " + exception.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("All Assertion Information Fetching")
    @GetMapping("/assertion")
    public ResponseEntity findAllAssertion() {
        ArrayList<Assertion> listOfAssertion;
        try {
            listOfAssertion = assertionService.findAllAssertion();
            return ResponseEntity.ok(listOfAssertion);
        } catch (DuplicateKeyException | DatabaseEmptyException exception) {
            logger.info("Exception in finding all assertion data " + exception.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Assertion Information Fetching by assertion Id")
    @GetMapping("/assertion/{assertionId}")
    public ResponseEntity findByAssertionId(@PathVariable("assertion") String assertionId) {
        try {
            return ResponseEntity.ok(assertionService.findByAssertionId(assertionId));
        } catch (AssertionNotFoundException exception) {
            logger.info("Exception in finding a particular assertion data " + exception.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Assertion Information Fetching by Badge Id")
    @GetMapping("/assertion/badge/{badgeId}")
    public ArrayList<Assertion> findAssertionByBadge(@PathVariable("badgeId") String badgeId) {
        ArrayList<Assertion> listOfAssertions;
        try {
            listOfAssertions = assertionService.findByBadge(badgeId);
            return listOfAssertions;
        } catch (AssertionNotFoundException exception) {
            logger.info("Exception in finding assertion data by badge id " + exception.getMessage());
            return null;
        }
    }
    @ApiOperation("Assertion Information Fetching by Badge Id")
    @GetMapping("/assertion/issuer/{issuerId}")
    public ArrayList<Assertion> findAssertionByIssuerId(@PathVariable("issuerId") String issuerId) {
        ArrayList<Assertion> listOfAssertions;
        try {
            listOfAssertions = assertionService.findByIssuerId(issuerId);
            return listOfAssertions;
        } catch (AssertionNotFoundException exception) {
            logger.info("Exception in finding assertion data by Issuer id " + exception.getMessage());
            return null;
        }
    }

    @ApiOperation("Updating Assertion Model After Revocation")
    @PatchMapping("assertion/{assertionId}")
    public Assertion updateAssertionAfterRevocation(@PathVariable("assertionId") String assertionId,
                                                    @RequestBody String revocationReason) {
        return assertionService.updateAssertionAfterRevocation(assertionId,revocationReason);
    }

    @ApiOperation("Verifying Assertion Of A Particular Issuer")
    @PostMapping("assertion/{assertionId}/verify")
    public boolean verify(@PathVariable("assertionId") String assertionId, @RequestBody Assertion assertion) throws AssertionNotFoundException {
        System.out.println("printing verify assertion");
        System.out.println("verify assertion"+assertion);
        Assertion assertionFromDB = assertionService.findByAssertionId(assertionId);
        System.out.println("assertion from db"+assertionFromDB);
        return assertionFromDB.isEqual(assertion);
    }
}