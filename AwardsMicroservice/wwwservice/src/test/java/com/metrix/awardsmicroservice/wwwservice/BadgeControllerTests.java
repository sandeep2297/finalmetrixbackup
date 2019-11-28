package com.metrix.awardsmicroservice.wwwservice;



import com.metrix.awardsmicroservice.wwwservice.controller.BadgeController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WwwserviceApplication.class)
@TestPropertySource("/application-test.properties")
public class BadgeControllerTests {

    @Autowired
    private BadgeController badgeController;

    @Test
    public void testController() throws Exception {
        assertThat(badgeController).isNotNull();
    }
}
