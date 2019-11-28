package com.metrix.awardsmicroservice.libs.service;

import com.metrix.awardsmicroservice.libs.exception.AssertionNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.Assertion;
import com.mongodb.DuplicateKeyException;

import java.util.ArrayList;

public interface IAssertionService {
    Assertion saveAssertion(Assertion assertion) throws DuplicateKeyException;

    ArrayList<Assertion> findAllAssertion() throws DatabaseEmptyException;

    Assertion findByAssertionId(String assertionId) throws AssertionNotFoundException;

    ArrayList<Assertion> findByBadge(String badge) throws AssertionNotFoundException;

    ArrayList<Assertion> findByIssuerId(String issuerId) throws AssertionNotFoundException;

    void deleteByAssertionId(String assertionId) throws AssertionNotFoundException;
}