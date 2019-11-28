package com.metrix.ruleengine.ruleInitiator;

import com.metrix.libs.model.UserData;

/*This class will take input of the expression as lhs, rhs and the operator between them and then it sends this
 * data to arithmetic class to get the result of that expression  */
public class ReadRules {

    public boolean findOperation(UserData entity, String lhs, String operation, String rhs) {
        Arithmetic operator = new Arithmetic();
        lhs = entity.find(lhs).toLowerCase();
        boolean condition = false;
        if (operation.equals(">") || operation.toLowerCase().equals("greaterthan"))
            condition = operator.greaterThan(Integer.parseInt(lhs), Integer.parseInt(rhs));
        else if (operation.equals("<") || operation.toLowerCase().equals("lessthan"))
            condition = operator.lessThan(Integer.parseInt(lhs), Integer.parseInt(rhs));
        else if (operation.equals(">=") || operation.toLowerCase().equals("greaterthanandequalto"))
            condition = operator.greaterThanEquals(Integer.parseInt(lhs), Integer.parseInt(rhs));
        else if (operation.equals("<=") || operation.toLowerCase().equals("lessthanandequalto"))
            condition = operator.greaterThanEquals(Integer.parseInt(lhs), Integer.parseInt(rhs));
        else if (operation.equals("==") || operation.equals("=") || operation.toLowerCase().equals("equals"))
            condition = operator.equals(lhs, rhs);

        return condition;
    }
}
