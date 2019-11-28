package com.metrix.awardsmicroservice.wwwservice.controller;

import com.metrix.awardsmicroservice.libs.exception.BadgeClassNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.BadgeClass;
import com.metrix.awardsmicroservice.libs.service.BadgeClassServiceImpl;
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
public class BadgeClassController {

    private static Logger logger = LogManager.getLogger(BadgeController.class);

    @Autowired
    BadgeClassServiceImpl badgeClassService;

    @ApiOperation("Badge Instance Information Saving")
    @PostMapping("/badgeclass")
    public ResponseEntity<BadgeClass> saveBadge(@RequestBody BadgeClass badgeClass) {
        try {
            return ResponseEntity.ok(badgeClassService.saveBadgeClass(badgeClass));
        } catch (DuplicateKeyException exception) {
            logger.info("Exception in posting Badge Instance data "+ exception.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("All Badge Instance Information Fetching")
    @GetMapping ("/badgeclass")
    public ResponseEntity findAllBadgeClass() {
        ArrayList<BadgeClass> badgeClassArrayList;
        try {
            badgeClassArrayList = badgeClassService.findAllBadgeClass();
            return ResponseEntity.ok(badgeClassArrayList);
        } catch (DatabaseEmptyException exception) {
            logger.info("Exception in finding all badge instances data " + exception.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation("Badge Instance Information Fetching by Badge Instance Id")
    @GetMapping("/badgeclass/{badgeclassid}")
    public ResponseEntity findByBadgeClassId(@PathVariable("badgeclassid") String badgeclassid) {
        try {
            return ResponseEntity.ok(badgeClassService.findByBadgeClassId(badgeclassid));
        } catch (BadgeClassNotFoundException exception) {
          logger.info("Exception in finding a particular badge instance data " + exception.getMessage());
          return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Badge Instance Information Fetching by Issuer Id")
    @GetMapping("/badge/issuer/{issuerid}")
    public ArrayList<BadgeClass> findBadgeClassByIssuerId(@PathVariable("issuerid") String issuerid) {
        ArrayList<BadgeClass> badgeClassArrayList;
        try {
            badgeClassArrayList = badgeClassService.findBadgeClassByIssuerId(issuerid);
            return badgeClassArrayList;
        } catch (BadgeClassNotFoundException exception) {
            logger.info("Exception in finding badge instance by issuer id: " + exception.getMessage());
            return null;
        }
    }
}
