package com.metrix.activitypipelinemicroservice.controller;

import com.metrix.activitypipelinemicroservice.model.Expression;
import com.metrix.activitypipelinemicroservice.service.ExpressionServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1")
public class ExpressionController {

    @Autowired
    ExpressionServiceImpl expressionServiceImpl;

    // Get mapping for fetching expressions by rule engine
    @ApiOperation("Getting an expression by Rule Id")
    @GetMapping("/expression/{ruleid}")
    public ArrayList<Expression> getExpression(@PathVariable("ruleid") String ruleid) {
        return expressionServiceImpl.findExpressionByRuleId(ruleid);
    }
}
