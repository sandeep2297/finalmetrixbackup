package com.metrix.activitypipelinemicroservice.service;

import com.metrix.activitypipelinemicroservice.model.Expression;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;

public interface IExpressionService {
    ArrayList<Expression> findExpressionByRuleId(String ruleId);

    Expression addExpression(Expression expression) throws DuplicateKeyException;
}
