package com.metrix.awardsmicroservice.libs.service;

import com.metrix.awardsmicroservice.libs.exception.AssertionNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.Assertion;
import com.metrix.awardsmicroservice.libs.repository.IAssertionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;

@Service
public class AssertionServiceImpl implements IAssertionService{

    @Autowired
    IAssertionRepository assertionRepository;

    public Assertion saveAssertion(Assertion assertion) throws DuplicateKeyException {
        if (assertionRepository.findByAssertionId(assertion.getAssertionId()) == null) {
            return assertionRepository.save(assertion);
        } else {
            throw new DuplicateKeyException("Assertion already exist with assertion id: " + assertion.getAssertionNarrative());
        }
    }

    public ArrayList<Assertion> findAllAssertion() throws DatabaseEmptyException {
        if (assertionRepository.findAll().size() == 0) {
            throw new DatabaseEmptyException("Database is empty no assertion found");
        } else {
            return assertionRepository.findAll();
        }
    }

    public Assertion findByAssertionId(String assertionId) throws AssertionNotFoundException {
        if (assertionRepository.findByAssertionId(assertionId) != null) {
            return assertionRepository.findByAssertionId(assertionId);
        } else {
            throw new AssertionNotFoundException("Assertion not found with assertion id: " + assertionId);
        }
    }

    public ArrayList<Assertion> findByBadge(String badge) throws AssertionNotFoundException{
        ArrayList<Assertion> assertionArrayList;
        if (assertionRepository.findByBadge(badge) != null) {
            assertionArrayList = assertionRepository.findByBadge(badge);
            return assertionArrayList;
        } else {
            throw new AssertionNotFoundException("Assertion not found with client id: " + badge);
        }
    }

    public ArrayList<Assertion> findByIssuerId(String issuerId) throws AssertionNotFoundException{
        ArrayList<Assertion> assertionArrayList;
        if (assertionRepository.findByIssuerId(issuerId) != null) {
            assertionArrayList = assertionRepository.findByIssuerId(issuerId);
            return assertionArrayList;
        } else {
            throw new AssertionNotFoundException("Assertion not found with issuer id: " + issuerId);
        }
    }

    public void deleteByAssertionId(String assertionId) throws AssertionNotFoundException {
        if (assertionRepository.findByAssertionId(assertionId) != null) {
            assertionRepository.deleteByAssertionId(assertionId);
        } else {
            throw new AssertionNotFoundException("Assertion not found with badge id: " + assertionId);
        }
    }

    public Assertion updateAssertionAfterRevocation(String assertionId, String revocationReason) {
        Assertion assertionFromDb = assertionRepository.findByAssertionId(assertionId);
        assertionFromDb.setRevocationReason(revocationReason);
        assertionFromDb.setRevoked(true);
        return assertionRepository.save(assertionFromDb);
    }

}