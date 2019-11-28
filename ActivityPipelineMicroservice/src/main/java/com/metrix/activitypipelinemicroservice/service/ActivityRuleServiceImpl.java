package com.metrix.activitypipelinemicroservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metrix.activitypipelinemicroservice.exception.DataNotFoundException;
import com.metrix.activitypipelinemicroservice.model.ActivityRule;
import com.metrix.activitypipelinemicroservice.model.Award;
import com.metrix.activitypipelinemicroservice.model.Expression;
import com.metrix.activitypipelinemicroservice.model.ExpressionTree;
import com.metrix.activitypipelinemicroservice.repository.IActivityRuleRepository;
import com.metrix.activitypipelinemicroservice.rule.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
public class ActivityRuleServiceImpl implements IActivityRuleService {

    @Autowired
    private ExpressionServiceImpl expressionServiceImpl;

    @Autowired
    private IActivityRuleRepository activityRuleRepo;

    // Service to save activity rule in database
    public ActivityRule addActivityRule(String issuerProfileId, String pipelineId, HashMap<String, Object> map) throws DuplicateKeyException {

        // To convert json to object format
        ObjectMapper mapper = new ObjectMapper();
        Rule rule = mapper.convertValue(map, Rule.class);

        // Setting basic details for the rule
        ActivityRule activityRule = new ActivityRule();
        activityRule.setActivityPipelineId(pipelineId);
        activityRule.setCreatedBy(rule.getCreatedBy());
        activityRule.setUpdatedBy(rule.getCreatedBy());
        activityRule.setArchieved(false);
        activityRule.setCreatedOn(LocalDateTime.now());
        activityRule.setUpdatedOn(LocalDateTime.now());
        activityRule.setActivityRuleId(UUID.randomUUID().toString());
        activityRule.setRuleName(rule.getRuleName());
        activityRule.setRuleDescription(rule.getRuleDescription());

        // Declaring arrays and arraylist to store rules in the format required by rule engine
        int ruleCount = rule.getExpression().size();
        int awardCount = 2;
        String[] expressionId = new String[ruleCount];
        Expression[] expression = new Expression[ruleCount];
        ExpressionTree[] expressionTree = new ExpressionTree[ruleCount];
        Award[] award = new Award[awardCount];
        ArrayList<ExpressionTree> expressionList = new ArrayList<ExpressionTree>(ruleCount);
        ArrayList<Award> awardList = new ArrayList<Award>(ruleCount);

        // Generating unique Id for all expressions
        for (int i = 0; i < ruleCount; i++)
            expressionId[i] = UUID.randomUUID().toString();

        // To store expressions, expression-flow into database, expression-tree
        for (int j = 0; j < ruleCount; j++) {
            expression[j] = new Expression();
            expression[j].setActivityRuleId(activityRule.getActivityRuleId());
            expression[j].setExpressionId(expressionId[j]);
            expression[j].setLhs(rule.getExpression().get(j).getType());
            expression[j].setOperator(rule.getExpression().get(j).getOperator());
            expression[j].setRhs(rule.getExpression().get(j).getExpressionValue());
            expressionServiceImpl.addExpression(expression[j]);
            expressionTree[j] = new ExpressionTree();
            expressionTree[j].setExpressionId(expressionId[j]);

            // To map Expression to Expression Tree
            if (j == ruleCount - 1) {
                expressionTree[j].setAndWith("NULL");
                expressionTree[j].setOrWith("NULL");
            } else {
                if (rule.getExpression().get(j + 1).getConjunction().equals("AND")) {
                    expressionTree[j].setAndWith(expressionId[j + 1]);
                    expressionTree[j].setOrWith("NULL");
                }
                if (rule.getExpression().get(j + 1).getConjunction().equals("OR")) {
                    expressionTree[j].setOrWith(expressionId[j + 1]);
                    expressionTree[j].setAndWith("NULL");
                }
            }
            expressionList.add(expressionTree[j]);
        }

        // To store awards information into rule
        for (int k = 0; k < awardCount; k++) {
            award[k] = new Award();
            if (k == 0) {
                if(rule.getPoint() != null && !rule.getPoint().equals("")) {
                    award[k].setAwardType("POINT");
                    award[k].setAwardValue(rule.getPoint());
                    awardList.add(award[k]);
                }
            }
            if (k == 1) {
                if(rule.getBadge() != null && !rule.getBadge().equals("")) {
                    award[k].setAwardType("BADGE");
                    award[k].setAwardValue(rule.getBadge());
                    awardList.add(award[k]);
                }
            }
//            awardList.add(award[k]);
        }
        activityRule.setExpressionTree(expressionList);
        activityRule.setAward(awardList);
        if (activityRuleRepo.findByActivityRuleId(activityRule.getActivityRuleId()) == null)
            return activityRuleRepo.save(activityRule);
        else {
            throw new DuplicateKeyException("rule already exists");
        }
    }

    // Service to return data by a particular id
    public ActivityRule findActivityRuleById(String activityRuleId) throws DataNotFoundException {
        if (activityRuleRepo.findByActivityRuleId(activityRuleId) != null)
            return activityRuleRepo.findByActivityRuleId(activityRuleId);
        else
            throw new DataNotFoundException("No rule with the id found => " + activityRuleId);
    }

    @Override
    public ArrayList<ActivityRule> findActivityPipelineById(String activityPipelineId) {
        return activityRuleRepo.findByActivityPipelineId(activityPipelineId);
    }

    // Service to change archive status of rule
    public ActivityRule patchArchieveStatusOfRule(String IssuerProfileId, String pipelineId, String ruleId, boolean archiveStatus) {
        ActivityRule activityRule = activityRuleRepo.findByActivityRuleId(ruleId);
        activityRule.setArchieved(archiveStatus);
        return activityRuleRepo.save(activityRule);
    }
}
