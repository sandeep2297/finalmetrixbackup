//package com.metrix.libs;
//import com.metrix.libs.model.*;
//import com.metrix.libs.repository.ResultRepository;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = LibsApplication.class)
//public class LibsDAOTest {
//    static List<ActivityExpressionResult> expressionResultList = new ArrayList<>();
//    static List<Award> awardList = new ArrayList<>();
//    @Autowired
//    private ResultRepository resultRepository;
//
//    private static ActivityRuleEvaluationResults activityRuleEvaluationResults;
//
//    @BeforeAll
//    public static void testSetup()
//    {
//
//        ActivityExpressionResult activityExpressionResult = new ActivityExpressionResult("1", true);
//        expressionResultList.add(activityExpressionResult);
//        Award awards = new Award("Point", "10");
//        awardList.add(awards);
//        UserData userData = new UserData("1", "commit", "push", "repository");
//        activityRuleEvaluationResults = new ActivityRuleEvaluationResults("1", "1", "1",
//                "harshgarg0906", "0", EvaluationStatus.PASSED,
//                expressionResultList, awardList, userData, LocalDateTime.now(), "system", LocalDateTime.now(), LocalDateTime.now()
//                , LocalDateTime.now());
//    }
//
//    @BeforeEach
//    public void saveData()
//    {
//        resultRepository.save(activityRuleEvaluationResults);
//    }
//    @AfterEach
//    public void deleteData()
//    {
//        resultRepository.deleteByEvaluationId("1");
//    }
//
//    @Test
//    public void testPosting()
//    {
//        assertEquals(1,resultRepository.findAll().size());
//    }
//
//    @Test
//    public void avoidDuplicate() {
//        ActivityRuleEvaluationResults testActivityRuleEvaluationResults=activityRuleEvaluationResults;
//        Assertions.assertThrows(DuplicateKeyException.class, () -> {
//            resultRepository.save(testActivityRuleEvaluationResults);
//        });
//    }
//
//
//    @Test
//    public void testGetActivityRuleEvaluationResultsDetails() {
//        activityRuleEvaluationResults = resultRepository.findByEvaluationId("1");
//        assertEquals("harshgarg0906", activityRuleEvaluationResults.getActorId());
//        assertNotNull(activityRuleEvaluationResults.getActorId());
//    }
//
//
//}
