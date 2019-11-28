package com.metrix.usermicroservice.repository;

import com.metrix.usermicroservice.model.ClientProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

/**
 *
 *
 * 
 * Repository for performing CRUD operations on the database
 */
public interface IClientProfileRepository extends MongoRepository<ClientProfile, String> {
	ArrayList<ClientProfile> findAll();

	ClientProfile findByProfileName(String ProfileName);

	ClientProfile findByClientEmail(String email);

}
