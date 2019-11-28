/**
 * Activity Pipeline Test Class
 */

package com.metrix.activitypipelinemicroservice;

import com.google.common.annotations.VisibleForTesting;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class ActivityPipelineMicroserviceApplicationTests {

    /**
     * Test Method Activity Pipeline
     */
    @VisibleForTesting
    void contextLoads() {
    }

}
