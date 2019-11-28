package com.metrix.usermicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;

import com.metrix.usermicroservice.exception.ClientNotFoundException;
import com.metrix.usermicroservice.exception.DatabaseEmptyException;
import com.metrix.usermicroservice.model.ClientProfile;
import com.metrix.usermicroservice.model.Metadata;
import com.metrix.usermicroservice.repository.IClientProfileRepository;

@SpringBootTest(classes = UserMicroserviceApplication.class)
@TestPropertySource("/application-test.properties")
public class ClientDaoTest {

	@Autowired
	private IClientProfileRepository clientRepo;

	private static ClientProfile clientProfile;

	@BeforeAll
	public static void clientSetup() {
		Metadata meta = new Metadata("Metadata");
		ArrayList<String> clientlist = new ArrayList<>();
		clientlist.add("Sachin");
		clientlist.add("Sehwag");
		clientProfile = new ClientProfile("2","Sandy", "Sandy", "sandybhndr95@gmail.com", "http://www.google.com", "ITC",
				"itc@itcinfotech.com", "http://www.itc.com", meta, clientlist, LocalDateTime.now(), "Owner",
				LocalDateTime.now(), "Owner", "HR", "SBC");
	}

	@Test
	public void addClient() {
		clientRepo.save(clientProfile);
	}

//	@Test
//	public void avoidDuplicate() {
//		Assertions.assertThrows(DuplicateKeyException.class, () -> {
//			clientRepo.save(clientProfile);
//		});
//	}

	@Test
	public void getClientProfileDetails() throws DatabaseEmptyException, ClientNotFoundException {
		clientProfile = clientRepo.findByProfileName("Sandy");
		assertEquals("Sandy", clientProfile.getOwnedBy());
		assertNotNull(clientProfile.getOwnedBy());
		assertEquals("sandybhndr95@gmail.com", clientProfile.getClientEmail());
		assertNotNull(clientProfile.getClientEmail());
		assertEquals("ITC", clientProfile.getOrganisationName());
		assertNotNull(clientProfile.getOrganisationName());
	}

}
