package com.metrix.activitypipelinemicroservice;

import com.metrix.activitypipelinemicroservice.model.IssuerProfile;
import com.metrix.activitypipelinemicroservice.model.Status;
import com.metrix.activitypipelinemicroservice.service.IssuerProfileServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ActivityPipelineMicroserviceApplication.class)
@TestPropertySource("/application-test.properties")
public class IssuerProfileTests {

    // Static instance of IssuerProfile class
    static IssuerProfile issuerProfileGlobal;
    @Autowired
    private IssuerProfileServiceImpl issuerProfileServiceImplMockDb;

    // Before all method to create an object of IssuerProfile class
    @BeforeAll
    public static void beforeAll() {
        issuerProfileGlobal = new IssuerProfile("123", "itc", "itc@gmail.com", "itcinfotech", "User", Status.ACTIVE, "http://img1.com", "http:img2.com", "itc issuer", "to award points", LocalDateTime.now(), "ITC", LocalDateTime.now(), "ITC");
    }

    // Test case to check duplicate key is not inserted in database
    @Test
    public void duplicateEntry() {
        issuerProfileServiceImplMockDb.addIssuer(issuerProfileGlobal);
        IssuerProfile issuerProfile = new IssuerProfile("123", "itc", "itc@gmail.com", "itcinfotech", "User", Status.ACTIVE, "http://img1.com", "http:img2.com", "itc issuer", "to award points", LocalDateTime.now(), "ITC", LocalDateTime.now(), "ITC");
        assertThrows(DuplicateKeyException.class, () -> {
            issuerProfileServiceImplMockDb.addIssuer(issuerProfile);
        });
    }

    // Test case to check whether data is being inserted into database
    @Test
    public void addIssuersToDatabase() {
        IssuerProfile issuerProfile = new IssuerProfile("1234", "itcinfo", "itc@gmail.com", "itcinfotech", "User", Status.ACTIVE, "http://img1.com", "http:img2.com", "itc issuer", "to award points", LocalDateTime.now(), "ITC", LocalDateTime.now(), "ITC");
        issuerProfileServiceImplMockDb.addIssuer(issuerProfile);
        assertEquals(2, issuerProfileServiceImplMockDb.findByClientId("itcinfotech").size());
    }
}

