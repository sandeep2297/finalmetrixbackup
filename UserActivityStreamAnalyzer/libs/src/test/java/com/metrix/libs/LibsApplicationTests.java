package com.metrix.libs;
import com.metrix.libs.exception.ResultsNotFoundException;
import com.metrix.libs.model.*;
import com.metrix.libs.service.ResultService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LibsApplication.class)
public class LibsApplicationTests {
    static ActivityRuleEvaluationResults activityRuleEvaluationResults;
    static List<ActivityExpressionResult> expressionResultList = new ArrayList<>();
    static List<Award> awardList = new ArrayList<>();
    @Autowired
    private ResultService resultService;
    @BeforeAll
    public static void beforeAll() {
        ActivityExpressionResult activityExpressionResult = new ActivityExpressionResult("1", true);
        expressionResultList.add(activityExpressionResult);
        Award awards = new Award("Point", "10");
        awardList.add(awards);
        UserData userData = new UserData("1", "commit", "push", "repository");
        activityRuleEvaluationResults = new ActivityRuleEvaluationResults("1", "1", "1",
                "harshgarg0906", "0", EvaluationStatus.PASSED,
                expressionResultList, awardList, userData, LocalDateTime.now(), "system", LocalDateTime.now(), LocalDateTime.now()
                , LocalDateTime.now());
    }
    // To  check whether data is storing or not


    @Test
    public void addResultToDatabase() {
        ActivityExpressionResult activityExpressionResult = new ActivityExpressionResult("2", false);
        expressionResultList.add(activityExpressionResult);
        Award awards = new Award("Point", "100");
        awardList.add(awards);
        UserData userData = new UserData("1", "commit", "push", "repository");
        activityRuleEvaluationResults = new ActivityRuleEvaluationResults("2", "2", "2",
                "mahak0908", "1", EvaluationStatus.PASSED,
                expressionResultList, awardList, userData, LocalDateTime.now(), "system", LocalDateTime.now(), LocalDateTime.now()
                , LocalDateTime.now());
        resultService.saveData(activityRuleEvaluationResults);
        assertEquals(2, resultService.findResults().size());
    }
    //To check for duplicate data
    @Test
    public void testDuplicateEntry() {
        resultService.saveData(activityRuleEvaluationResults);
        ActivityRuleEvaluationResults activityRuleEvaluationResult;
        ActivityExpressionResult activityExpressionResult = new ActivityExpressionResult("2", true);
        expressionResultList.add(activityExpressionResult);
        Award awards = new Award("Point", "10");
        awardList.add(awards);
        UserData userData = new UserData("1", "commit", "push", "repository");
        activityRuleEvaluationResult = new ActivityRuleEvaluationResults("1", "1", "1",
                "harshgarg0906", "0", EvaluationStatus.PASSED,
                expressionResultList, awardList, userData, LocalDateTime.now(), "system", LocalDateTime.now(), LocalDateTime.now()
                , LocalDateTime.now());
        assertThrows(DuplicateKeyException.class, () -> {
            resultService.saveData(activityRuleEvaluationResults);
        });
    }

}








