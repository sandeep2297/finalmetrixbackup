//package com.metrix.ruleengine;
//    @Test
//    @DisplayName("Test Rule Engine")
//    public void testRuleEngine() throws Exception {
//        RulesEngine rulesEngine = new RulesEngine();
//        ExpressionTree expressionTree = new ExpressionTree("01", "02", "NULL");
//        ArrayList<ExpressionTree> expressionTreeArrayList = new ArrayList<>();
//        expressionTreeArrayList.add(expressionTree);
//        expressionTree = new ExpressionTree("02", "NULL", "NULL");
//        expressionTreeArrayList.add(expressionTree);
//        ArrayList<Award> awardList = new ArrayList<>();
//        awardList.add(new Award("points", "10"));
//        ActivityRule activityRule = new ActivityRule("01", "01", "", "",
//                expressionTreeArrayList, awardList, "poshan", LocalDateTime.now(), LocalDateTime.now(),
//                "poshan");
//        ArrayList<Expression> expressionsList = new ArrayList<>();
//        Expression expression = new Expression("01", "01", "ACTOR", "=", "user");
//        expressionsList.add(expression);
//        expression = new Expression("02", "01", "TYPE", "=", "push");
//        expressionsList.add(expression);
//        UserData userObject = new UserData("https://gitlab.stackroute.in/Harsh.Garg/webhook", "commit", "push", "repository",
//                "https://gitlab.stackroute.in/Harsh.Garg/webhook", "user", "Harsh.Garg");
//        ActivityRuleEvaluationResults results = new ActivityRuleEvaluationResults(UUID.randomUUID().toString(),
//                activityRule.getActivityPipelineId(), "01", userObject.getActorId(),
//                activityRule.getActivityRuleId(),userObject, LocalDateTime.now(), "system", LocalDateTime.now(),
//                LocalDateTime.now());
//        RuleActivityData ruleActivityData=new RuleActivityData(activityRule, results, expressionsList);
//        results = rulesEngine.init(ruleActivityData);
//        assertEquals(results.getAwards().get(0).getAwardType(), "points");
//        assertEquals(results.getAwards().get(0).getAwardValue(), "10");
//    }
