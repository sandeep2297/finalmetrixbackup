package com.metrix.awardsmicroservice.libs;


import com.metrix.awardsmicroservice.libs.model.Assertion;
import com.metrix.awardsmicroservice.libs.model.Evidence;
import com.metrix.awardsmicroservice.libs.model.Recipient;
import com.metrix.awardsmicroservice.libs.model.Verification;
import com.metrix.awardsmicroservice.libs.repository.IAssertionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AwardsLibsApplication.class)
@TestPropertySource("/application-test.properties")
public class AssertionDAOTests {

    @Autowired
    private IAssertionRepository assertionRepository;

    private static Assertion assertion;

    @BeforeAll
    public static void assertionClassSetup() {

        Recipient recipient = new Recipient();
        recipient.setRecipientId("1");
        recipient.setType("recipient");
        recipient.setHashed(true);
        recipient.setSalt("salt");
        recipient.setRecipientName("Metrix User");

        Verification verification = new Verification();
        verification.setType("verification");
        verification.setVerificationProperty("1");
        verification.setStartsWith("https://www.itcinfotech.com/");

        ArrayList<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("https://www.itc.com");
        allowedOrigins.add("https://www.itcinfotech.com");

        verification.setAllowedOrigins(allowedOrigins);

        Evidence evidence = new Evidence();
        evidence.setEvidenceURI("https://example.org/beths-robot-work.html");
        evidence.setNarrative("The student worked very hard to assemble and " +
                "present a robot. She documented the process with photography and text.");
        evidence.setName("My Robot");
        evidence.setDescription("A webpage with a photo and a description" +
                " of the robot the student built for this project.");
        evidence.setGenre("ePortfolio");
        evidence.setAudience("Robotics People");

        ArrayList<Evidence> evidences = new ArrayList<>();
        evidences.add(0,evidence);

        assertion = new Assertion("11","1","assertion",
                recipient,"11",verification, LocalDateTime.now(),
                "https://example.org/beths-robot-work.html",evidences,
                "Follow precisely a complex multistep procedure when" +
                        " carrying out experiments, taking measurements, or performing technical\n" +
                        " tasks; analyze the specific results based on explanations in the text",
                LocalDateTime.now(),false,"not revoked","1","commit",
                "repository","github","Commit Badge");
    }

    @BeforeEach
    public void saveData() {
        assertionRepository.save(assertion);
    }

    @AfterEach
    public void deleteAssetion() {
        assertionRepository.deleteByAssertionId("1");
    }

    @Test
    public void testPosting() {
        assertEquals(1, assertionRepository.findAll().size());
    }

    @Test
    public void testGetAssertionDetails() {
        assertion = assertionRepository.findByAssertionId("1");
        assertEquals("assertion", assertion.getType());
        assertNotNull(assertion.getType());
    }

//    @Test
//    public void avoidDuplicate() {
//        Assertion testAssertion = assertion;
//        Assertions.assertThrows(DuplicateKeyException.class, () -> {
//            assertionRepository.save(testAssertion);
//        });
//    }
}
