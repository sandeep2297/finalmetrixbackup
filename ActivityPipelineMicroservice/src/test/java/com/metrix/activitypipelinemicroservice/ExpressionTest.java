package com.metrix.activitypipelinemicroservice;

import com.metrix.activitypipelinemicroservice.model.Expression;
import com.metrix.activitypipelinemicroservice.service.ExpressionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ActivityPipelineMicroserviceApplication.class)
@TestPropertySource("/application-test.properties")
public class ExpressionTest {

    @Autowired
    ExpressionServiceImpl expressionService;

    // To check whether data is inserted into the database
    @Test
    public void addExpression() {
        Expression expression = new Expression("100rule1", "100exp1", "ACTOR", "=", "USER");
        expressionService.addExpression(expression);
        assertEquals(1, expressionService.findExpressionByRuleId("100exp1").size());
    }

    // To check whether duplicate data is inserted into the database
    @Test
    public void duplicateData() {
        Expression expression = new Expression("100rule3", "100exp3", "ACTOR", "=", "USER");
        expressionService.addExpression(expression);
        assertThrows(DuplicateKeyException.class, () -> {
            expressionService.addExpression(expression);
        });
    }
}
