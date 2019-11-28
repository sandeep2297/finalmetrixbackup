package com.metrix.usermicroservice.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metrix.usermicroservice.exception.DatabaseEmptyException;
import com.metrix.usermicroservice.model.ClientProfile;
import com.metrix.usermicroservice.service.ClientProfileServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1")
public class ClientProfileController {

	@Autowired
	ClientProfileServiceImpl clientService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String Owner = "OWNER";

	/**
	 *
	 *
	 * Saving Client Profile Information in database
	 * 
	 */
	@ApiOperation("saving client profile information")
	@PostMapping("/user")
	public ResponseEntity saveClient(@RequestBody ClientProfile clientProfile) {
		clientProfile.setOwnedBy(clientProfile.getProfileName());
		clientProfile.setCreatedAt(LocalDateTime.now());
		clientProfile.setCreatedBy(Owner);
		clientProfile.setUpdatedAt(LocalDateTime.now());
		clientProfile.setUpdatedBy(Owner);
		try {
			return ResponseEntity.ok(clientService.addClient(clientProfile));
		} catch (DuplicateKeyException exception) {
			logger.error("In save client method " + LocalDateTime.now() + " " + exception.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} catch (Exception exception) {
			logger.error("In save client method " + LocalDateTime.now() + " " + exception.getMessage());
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 *
	 *
	 * Saving Client Profile Information in database
	 * 
	 */
	@ApiOperation("updating client profile information")
	@PutMapping("/user")
	public ResponseEntity updateClientData(@RequestParam("clientEmail") String clientEmail,
			@RequestBody ClientProfile clientProfile) {
		return ResponseEntity.ok(clientService.patchClientData(clientEmail,clientProfile));
	}

	/**
	 * Retrieving All Client Information from the database
	 */

	@ApiOperation("retrieving all client information")
	@GetMapping("/user/profile")
	public ResponseEntity getClientProfile() {
		ArrayList<ClientProfile> listOfClientProfile;
		try {
			listOfClientProfile = clientService.findAllClients();
			return ResponseEntity.ok(listOfClientProfile);
		} catch (DatabaseEmptyException exception) {
			logger.error("In get client profile method " + LocalDateTime.now() + " " + exception.getMessage());
			return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		} catch (Exception exception) {
			logger.error("In get client profile method " + LocalDateTime.now() + " " + exception.getMessage());
			return new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Retrieving Client Information from the database on the basis of profile name
	 */
	@ApiOperation("Retrieving client information on the basis of Client Email")
	@GetMapping("/user/clientprofile")
	public ResponseEntity getClientProfileByName(@RequestParam("clientEmail") String clientEmail) {
		return ResponseEntity.ok(clientService.findClientByEmail(clientEmail));
	}
}
