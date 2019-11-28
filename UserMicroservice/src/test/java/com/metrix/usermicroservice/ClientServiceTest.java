package com.metrix.usermicroservice;

import com.metrix.usermicroservice.exception.ClientNotFoundException;
import com.metrix.usermicroservice.exception.DatabaseEmptyException;
import com.metrix.usermicroservice.model.ClientProfile;
import com.metrix.usermicroservice.model.Metadata;
import com.metrix.usermicroservice.service.IClientProfileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = UserMicroserviceApplication.class)
@TestPropertySource("/application-test.properties")
public class ClientServiceTest {

	private static ClientProfile clientProfile;
	@Autowired
	private IClientProfileService clientService;

	@BeforeAll
	public static void clientSetup() {
		Metadata meta = new Metadata("Metadata");
		ArrayList<String> clientlist = new ArrayList<>();
		clientlist.add("Akhilesh");
		clientlist.add("Poshan");
		clientProfile = new ClientProfile("1", "Sandeep", "Sandeep", "sandybhndr05@gmail.com", "http://www.google.com",
				"ITC", "itc@itcinfotech.com", "http://www.itc.com", meta, clientlist, LocalDateTime.now(), "Owner",
				LocalDateTime.now(), "Owner", "HR", "SBC");
	}

	@Test
	public void addClient() {
		clientService.addClient(clientProfile);
	}

    @Test
    public void avoidDuplicate() {
        Assertions.assertThrows(DuplicateKeyException.class, () -> {
            clientService.addClient(clientProfile);
        });
    }

	@Test
	public void getClientProfileDetails() throws DatabaseEmptyException, ClientNotFoundException {
		clientProfile = clientService.findClientByProfileName("Sandeep");
		assertEquals("Sandeep", clientProfile.getOwnedBy());
		assertNotNull(clientProfile.getOwnedBy());
		assertEquals("sandybhndr05@gmail.com", clientProfile.getClientEmail());
		assertNotNull(clientProfile.getClientEmail());
		assertEquals("ITC", clientProfile.getOrganisationName());
		assertNotNull(clientProfile.getOrganisationName());
	}

}
