package com.metrix.activitypipelinemicroservice.repository;

import com.metrix.activitypipelinemicroservice.model.Expression;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface IExpressionRepository extends MongoRepository<Expression, String> {
    Expression findByExpressionId(String expression);

    ArrayList<Expression> findByActivityRuleId(String activityRuleId);
}
