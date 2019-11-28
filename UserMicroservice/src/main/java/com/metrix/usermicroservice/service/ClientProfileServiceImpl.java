package com.metrix.usermicroservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.metrix.usermicroservice.exception.ClientNotFoundException;
import com.metrix.usermicroservice.exception.DatabaseEmptyException;
import com.metrix.usermicroservice.model.ClientProfile;
import com.metrix.usermicroservice.repository.IClientProfileRepository;

@Service
public class ClientProfileServiceImpl implements IClientProfileService {

	@Autowired
	private IClientProfileRepository clientRepo;

	private static final String Owner = "OWNER";

	// For getting all the client profile data from the database
	public ArrayList<ClientProfile> findAllClients() throws DatabaseEmptyException {
		if (clientRepo.findAll().size() == 0) {
			throw new DatabaseEmptyException("Database is Empty");
		} else {
			return clientRepo.findAll();
		}
	}

	// For getting client profile data form the database using profile name
	public ClientProfile findClientByProfileName(String profilename) throws ClientNotFoundException {
		if (clientRepo.findByProfileName(profilename) != null) {
			return clientRepo.findByProfileName(profilename);
		} else {
			throw new ClientNotFoundException("Client not found with profilename" + profilename);
		}
	}

	// For posting the client profile data into the database
	public ClientProfile addClient(ClientProfile clientProfile) {
		if (clientRepo.findByClientEmail(clientProfile.getClientEmail()) == null) {
			return clientRepo.save(clientProfile);
		} else {
			throw new DuplicateKeyException("Client Already exist with email " + clientProfile.getClientEmail());
		}
	}

	// For getting the client profile data from the database using client email
	public ClientProfile findByEmail(String email) throws ClientNotFoundException {
		if (clientRepo.findByClientEmail(email) == null) {
			throw new ClientNotFoundException("Client does not exist with email " + email);
		} else {
			return clientRepo.findByClientEmail(email);
		}
	}

	// For getting the client data using client email
	public ClientProfile findClientByEmail(String clientEmail) {
		return clientRepo.findByClientEmail(clientEmail);
	}

	public ClientProfile patchClientData(String clientEmail, ClientProfile clientProfile) {
		ClientProfile client = clientRepo.findByClientEmail(clientEmail);
		client.setProfileName(clientProfile.getProfileName());
		client.setDescription(clientProfile.getDescription());
		client.setAvatarURL(clientProfile.getAvatarURL());
		client.setClientEmail(clientEmail);
		client.setAboutOrg(clientProfile.getAboutOrg());
		client.setOrganisationName(clientProfile.getOrganisationName());
		client.setOrganisationURL(clientProfile.getOrganisationURL());
		client.setOrgEmail(clientProfile.getOrgEmail());
		client.setOwnedBy(clientProfile.getProfileName());
		client.setCreatedAt(LocalDateTime.now());
		client.setCreatedBy(Owner);
		client.setUpdatedAt(LocalDateTime.now());
		client.setUpdatedBy(Owner);
		return clientRepo.save(client);
	}

}
