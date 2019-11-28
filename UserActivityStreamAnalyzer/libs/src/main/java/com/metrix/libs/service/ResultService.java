package com.metrix.libs.service;


import com.metrix.libs.exception.ResultsNotFoundException;
import com.metrix.libs.model.ActivityRuleEvaluationResults;
import com.metrix.libs.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * This class is the Service class to provide the abstraction between cotroller and the MongoRepository
 */
@Service
public class ResultService implements IResultService {

    @Autowired
    private ResultRepository resultRepository;

    //Saving the data into the database
    public ActivityRuleEvaluationResults saveData(ActivityRuleEvaluationResults ruleEvaluationData) {
        return resultRepository.save(ruleEvaluationData);
    }

    // Fetching all the data from the database
    public ArrayList<ActivityRuleEvaluationResults> findResults() {
        return resultRepository.findAll();
    }

    public ActivityRuleEvaluationResults findByEvaluationId(String evaluationId) throws ResultsNotFoundException {
        if (resultRepository.findByEvaluationId(evaluationId) != null) {
            return resultRepository.findByEvaluationId(evaluationId);
        } else {
            throw new ResultsNotFoundException("Result not found with result id: " + evaluationId);
        }

    }

    public void deleteByEvaluationId(String evaluationId)
    {
        resultRepository.deleteByEvaluationId(evaluationId);
    }

}
