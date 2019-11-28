package com.metrix.rules;

import com.metrix.libs.model.ActivityRule;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RuleNormalizer {
    public ArrayList<ActivityRule> checkRules(ArrayList<ActivityRule> activityRuleArrayList) {
        ArrayList<ActivityRule> activityRuleArrayList1 = new ArrayList<>();
        for (ActivityRule item : activityRuleArrayList) {
            if(!item.isArchieved()) activityRuleArrayList1.add(item);
        }
        return activityRuleArrayList1;
    }
}
