package com.metrix.awardsmicroservice.wwwservice;


import com.metrix.awardsmicroservice.wwwservice.controller.AssertionsController;
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
public class AssertionControllerTests {

    @Autowired
    private AssertionsController assertionsController;

    @Test
    public void testController() throws Exception {
        assertThat(assertionsController).isNotNull();
    }
}
