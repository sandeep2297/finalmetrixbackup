package com.metrix.activitypipelinemicroservice.service;

import com.metrix.activitypipelinemicroservice.model.Expression;
import com.metrix.activitypipelinemicroservice.repository.IExpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExpressionServiceImpl implements IExpressionService {

    @Autowired
    IExpressionRepository expressionRepository;

    // Service to save expression into database
    public Expression addExpression(Expression expression) throws DuplicateKeyException {
        if (expressionRepository.findByExpressionId(expression.getExpressionId()) == null)
            return expressionRepository.save(expression);
        else {
            throw new DuplicateKeyException("Expression already exists");
        }
    }

    public ArrayList<Expression> findExpressionByRuleId(String ruleId) {
        return expressionRepository.findByActivityRuleId(ruleId);
    }
}
