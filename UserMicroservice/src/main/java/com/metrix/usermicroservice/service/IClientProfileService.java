package com.metrix.usermicroservice.service;

import com.metrix.usermicroservice.exception.ClientNotFoundException;
import com.metrix.usermicroservice.exception.DatabaseEmptyException;
import com.metrix.usermicroservice.model.ClientProfile;

import java.util.ArrayList;

public interface IClientProfileService {

	ArrayList<ClientProfile> findAllClients() throws DatabaseEmptyException;

	ClientProfile findClientByProfileName(String profilename) throws ClientNotFoundException;

	ClientProfile addClient(ClientProfile clientProfile);

	ClientProfile findClientByEmail(String clientEmail);

	ClientProfile patchClientData(String clientEmail, ClientProfile clientProfile);

}
