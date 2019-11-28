package com.metrix.ruleengine.ruleInitiator;

import com.metrix.libs.model.Expression;
import com.metrix.libs.model.ActivityRule;
import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.libs.model.RuleActivityData;
import com.metrix.libs.model.ActivityExpressionResult;
import com.metrix.libs.model.ExpressionTree;
import com.metrix.libs.model.Award;
import com.metrix.libs.model.EvaluationStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class RulesEngine {

//    @Autowired
//    Sender sender;

    public static Logger logger = LogManager.getLogger(RulesEngine.class);

    Expression expression;
    ReadRules readRules;
    ActivityRule activityRule;
    String lhs;
    String operation;
    String rhs;

//    @KafkaListener(topics = "${app.topic.consumer}")
//    public void kafkalistener(@Payload RuleActivityData ruleActivityData){
//        ActivityRuleEvaluationResults entityResult = init(ruleActivityData);
//        sender.send(entityResult);
//    }

    /*The rule data, user data and the array of expression of this rule is received in this method and is passed to
	to the method for reading the expression and it will evaluate the result of each expression and store it in the array list.
	This array list along with expression which has the conjunction is passed to the rules evaluation method
	which produces the final result of rule*/
    public ActivityRuleEvaluationResults init(RuleActivityData ruleActivityData) {
        ActivityRule activityRule = ruleActivityData.getActivityRule();
        ActivityRuleEvaluationResults entityResult = ruleActivityData.getResults();
        ArrayList<Expression> expressionArrayList = ruleActivityData.getExpressionArrayList();
        this.activityRule = activityRule;
        HashMap<String, Boolean> results = new HashMap<>();
        List<ActivityExpressionResult> activityExpressionResultList = new ArrayList<>();
        for (int i = 0; i < expressionArrayList.size(); i++) {
            expression = expressionArrayList.get(i);
            readRules = new ReadRules();
            lhs = expression.getLhs();
            operation = expression.getOperator();
            rhs = expression.getRhs();

            Boolean tempValue = readRules.findOperation(entityResult.getUserData(), lhs, operation, rhs);
            results.put(expression.getExpressionId(), tempValue);
            ActivityExpressionResult activityExpressionResult = new ActivityExpressionResult(expression.getExpressionId(), tempValue);
            activityExpressionResultList.add(activityExpressionResult);
        }
        entityResult.setActivityExpressionResult(activityExpressionResultList);
//        entityResult = rulesEvaluation(results, entityResult);
        return rulesEvaluation(results, entityResult);
    }



    /*	Array of result of the expression and expression with contain the conjunctions between them is passed to get the
        final result of the rule and assign respective award to the user*/
    public ActivityRuleEvaluationResults rulesEvaluation(HashMap<String, Boolean> results, 
                                                         ActivityRuleEvaluationResults entityResult) {
        List<ExpressionTree> tree = activityRule.getExpressionTree();
        logger.info(tree);
        boolean first = results.get(tree.get(0).getExpressionId());
        boolean second;
        for (int i = 0; i < tree.size() - 1; i++) {
            if (tree.get(i).getAndWith() != "NULL") {
                second = results.get(tree.get(i).getAndWith());
                first = first && second;
            } else if (tree.get(i).getOrWith() != "NULL") {
                second = results.get(tree.get(i).getOrWith());
                first = first || second;
            }
        }

        if (first) {
            entityResult.setEvaluationStatus(EvaluationStatus.PASSED);
            Award award;
            List<Award> awardArrayList = entityResult.getAwards();
            if(awardArrayList==null) awardArrayList = new ArrayList<Award>();
            for (int i = 0; i < activityRule.getAward().size(); i++) {
                award = activityRule.getAward().get(i);
                awardArrayList.add(award);
                logger.info("Awards given of the rule: " +activityRule.getRuleName() +" :- " + award);
            }
            entityResult.setAwards(awardArrayList);
        }
        else {
            entityResult.setEvaluationStatus(EvaluationStatus.FAILED);
        }
        logger.info("Result set from rule engine: "+entityResult);
        return entityResult;
    }
}
