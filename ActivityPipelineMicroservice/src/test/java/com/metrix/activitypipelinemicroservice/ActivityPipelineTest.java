package com.metrix.activitypipelinemicroservice;

import com.metrix.activitypipelinemicroservice.exception.DataNotFoundException;
import com.metrix.activitypipelinemicroservice.model.ActivityPipeline;
import com.metrix.activitypipelinemicroservice.model.Headers;
import com.metrix.activitypipelinemicroservice.model.PipelineStatus;
import com.metrix.activitypipelinemicroservice.service.ActivityPipelineServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ActivityPipelineMicroserviceApplication.class)
@TestPropertySource("/application-test.properties")
public class ActivityPipelineTest {

    // Static instance of ActivityPipeline class
    private static ActivityPipeline activityPipeline;
    private static Headers headers;
    private static ArrayList<Headers> headersArrayList;
    @Autowired
    private ActivityPipelineServiceImpl activityPipelineServiceImpl;

    // Before all service creates an instance of activity pipeline
    @BeforeAll
    public static void valueSetup() {
        activityPipeline = new ActivityPipeline("1", "1", "101", "Kaliber", "This pipeline is for logging the data", "github", "https://abc.com", "stream/api/v1/issuer/issuserId/pipeline/pipelineId", PipelineStatus.DRAFTED, "tokenKey", headersArrayList, "Riya", LocalDateTime.now(), LocalDateTime.now(), "Riya");
    }

    //Test case to check whether data is being inserted into database
    @Test
    public void addActivityPipeline() throws DataNotFoundException {
        this.activityPipelineServiceImpl.addPipeline(activityPipeline);
        assertEquals(1, activityPipelineServiceImpl.getPipelineByIssuerId("101").size());
    }

    // Test case to check duplicate entry in database
    @Test
    public void checkDuplicateDataTest() {
        assertThrows(DuplicateKeyException.class, () -> {
            activityPipelineServiceImpl.addPipeline(activityPipeline);
        });
    }

    // Test case checking valid data entry
    @Test
    public void getActivityPipelineByIdTest() throws DataNotFoundException {
        assertEquals("101", activityPipelineServiceImpl.getPipelineByIssuerId(activityPipeline.getIssuerProfileId()).get(0).getIssuerProfileId());
    }
}
