package com.metrix.awardsmicroservice.wwwservice.controller;

import com.metrix.awardsmicroservice.libs.exception.BadgeNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.Badge;
import com.metrix.awardsmicroservice.libs.service.BadgeServiceImpl;
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
public class BadgeController {

    private static Logger logger = LogManager.getLogger(BadgeController.class);

    @Autowired
    BadgeServiceImpl badgeService;

    /**
     * Method to Post badge in the Awards Database
     */
    @ApiOperation("Badge Information Saving")
    @PostMapping("/badge")
    public ResponseEntity<Badge> saveBadge(@RequestBody Badge badge) {
        try {
            return ResponseEntity.ok(badgeService.saveBadge(badge));
        } catch (DuplicateKeyException exception) {
            logger.info("Exception in posting badge data " + exception.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get all the badges in the Awards Database
     */
    @ApiOperation("All Badge Information Fetching")
    @GetMapping("/badge")
    public ResponseEntity findAllBadges() {
        ArrayList<Badge> listOfBadges;
        try {
            listOfBadges = badgeService.findAllBadge();
            return ResponseEntity.ok(listOfBadges);
        } catch (DuplicateKeyException | DatabaseEmptyException exception) {
            logger.info("Exception in finding all badge data " + exception.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method to get badge in the Awards Database by Badge Id
     */
    @ApiOperation("Badge Information Fetching by Badge Id")
    @GetMapping("/badge/{badgeId}")
    public ResponseEntity findByBadgeId(@PathVariable("badgeId") String badgeId) {
        try {
            return ResponseEntity.ok(badgeService.findByBadgeId(badgeId));
        } catch (BadgeNotFoundException exception) {
            logger.info("Exception in finding a particular badge data " + exception.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Method to get badge in the Awards Database by Client Id
     */
    @ApiOperation("Badge Information Fetching by Client Id")
    @GetMapping("/badge/clientid/{clientId}")
    public ArrayList<Badge> findBadgesByClientId(@PathVariable("clientId") String clientId) {
        ArrayList<Badge> listOfBadges;
        try {
            listOfBadges = badgeService.findBadgesByClientId(clientId);
            return listOfBadges;
        } catch (BadgeNotFoundException exception) {
            logger.info("Exception in finding badge data by client id " + exception.getMessage());
            return null;
        }
    }
    @ApiOperation("Updating Badge Template Model After Editing")
    @PatchMapping("/badge/{badgeId}")
    public Badge updateBadge(@PathVariable("badgeId") String badgeId,
                                                    @RequestBody Badge newBadge) {
        return badgeService.updateBadge(badgeId,newBadge);
    }
}