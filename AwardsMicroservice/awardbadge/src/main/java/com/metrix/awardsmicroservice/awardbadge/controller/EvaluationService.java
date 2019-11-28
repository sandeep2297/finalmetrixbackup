package com.metrix.awardsmicroservice.awardbadge.controller;

import com.metrix.awardsmicroservice.libs.exception.BadgeClassNotFoundException;
import com.metrix.awardsmicroservice.libs.model.*;
import com.metrix.awardsmicroservice.libs.service.AssertionServiceImpl;
import com.metrix.awardsmicroservice.libs.service.BadgeClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EvaluationService {

    @Autowired
    private AssertionServiceImpl assertionService;

    @Autowired
    private BadgeClassServiceImpl badgeClassService;

    public void saveResults(ActivityRuleEvaluationResults evaluationResults) throws BadgeClassNotFoundException {

        System.out.println("Present 1");
        if (evaluationResults.getEvaluationStatus() == EvaluationStatus.PASSED && evaluationResults.getAwards() != null) {
            System.out.println("Present 2");
            for (int i = 0; i < evaluationResults.getAwards().size(); i++) {
                System.out.println("Present 3");
                Award award = evaluationResults.getAwards().get(i);
                if (award.getAwardType().equals("BADGE")) {
                    Recipient recipient = new Recipient();
                    recipient.setRecipientId(evaluationResults.getActorId());
                    recipient.setType("recipient");
                    recipient.setHashed(false);
                    recipient.setSalt("");

                    Verification verification = new Verification();
                    verification.setType("verification");

                    Assertion assertion = new Assertion();

                    assertion.setAssertionId(UUID.randomUUID().toString());
                    assertion.setType("assertion");
                    assertion.setRecipient(recipient);
                    assertion.setIssuerId(evaluationResults.getIssuerProfileId());

                    assertion.setImage(badgeClassService.findByBadgeClassId(award.getAwardValue()).getImage());

                    assertion.setVerification(verification);

                    assertion.setRevoked(false);
                    assertion.setRevocationReason("");

                    assertion.setIssuedOn(evaluationResults.getCreatedOn());
                    assertion.setTarget(evaluationResults.getUserData().getTargetObjectType());
                    assertion.setObject(evaluationResults.getUserData().getObjectContentType());
                    assertion.setVerb(evaluationResults.getUserData().getVerbs());

                    assertion.setBadge(award.getAwardValue());
                    assertion.setBadgeName(badgeClassService.findByBadgeClassId(award.getAwardValue()).getName());
                    System.out.println(assertion);
                    assertionService.saveAssertion(assertion);
                }
            }
        }
    }
}
