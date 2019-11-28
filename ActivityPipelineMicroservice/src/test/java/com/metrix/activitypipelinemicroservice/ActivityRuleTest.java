package com.metrix.activitypipelinemicroservice;

import com.metrix.activitypipelinemicroservice.model.ActivityRule;
import com.metrix.activitypipelinemicroservice.model.Award;
import com.metrix.activitypipelinemicroservice.model.ExpressionTree;
import com.metrix.activitypipelinemicroservice.repository.IActivityRuleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ActivityPipelineMicroserviceApplication.class)
@TestPropertySource("/application-test.properties")
public class ActivityRuleTest {

    // Static instance of ActivityRule class
    private static ActivityRule activityRule1;
    @Autowired
    private IActivityRuleRepository iActivityRuleRepository;

    // Before all service creates an instance of activity /home/test15/Desktop/merged_branch/metrix/docker_env/local/webappmicroservice-properties.envrule
    @BeforeAll
    public static void setValues() {
        ActivityRule activityRule = new ActivityRule();
        ActivityRule actRule = new ActivityRule();
        ArrayList<ExpressionTree> expTree = new ArrayList<>();
        ExpressionTree expressionTree1 = new ExpressionTree();
        expressionTree1.setExpressionId("1");
        expressionTree1.setAndWith("true");
        expressionTree1.setOrWith("false");
        expTree.add(expressionTree1);
        activityRule.setExpressionTree(expTree);
        expTree = activityRule.getExpressionTree();
        ArrayList<Award> award = new ArrayList<>();
        Award award1 = new Award();
        award1.setAwardType("Points");
        award1.setAwardValue("10");
        award.add(award1);
        activityRule.setAward(award);
        award = activityRule.getAward();
        activityRule1 = new ActivityRule("222", "1", "12", false, "rule 1", "for issuers", expTree, award, "ITC", LocalDateTime.now(), LocalDateTime.now(), "ITC");
    }

    // Test case to check whether data is being inserted into database
    @Test
    public void addActivityRulesTest() {
        iActivityRuleRepository.save(activityRule1);
        assertEquals(1, iActivityRuleRepository.findAll().size());
    }

    // Test case to check if proper data is inserted
    @Test
    public void findActivityRulesByIdTest() {
        String id = activityRule1.getActivityRuleId();
        assertEquals("1", iActivityRuleRepository.findByActivityRuleId(id).getActivityRuleId());
    }
}

