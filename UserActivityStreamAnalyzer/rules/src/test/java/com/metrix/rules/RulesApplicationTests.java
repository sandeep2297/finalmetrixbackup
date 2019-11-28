//package com.metrix.rules;
//
//import com.metrix.libs.model.UserData;
//import com.metrix.ruleengine.ReadRules;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = RulesApplication.class)
//@TestPropertySource("/application-test.properties")
//public class RulesApplicationTests {
//
//
//    @Test
//    @DisplayName("Test Find Operation Method")
//    public void testFindOperation() throws Exception {
//        UserData userData = new UserData("https://gitlab.stackroute.in/Harsh.Garg/webhook", "commit", "push", "repository",
//                "https://gitlab.stackroute.in/Harsh.Garg/webhook", "user", "Harsh.Garg");
//        ReadRules readRules = new ReadRules();
//        assertEquals(readRules.findOperation(userData, "TYPE", "=", "push"), true);
//    }
//}
