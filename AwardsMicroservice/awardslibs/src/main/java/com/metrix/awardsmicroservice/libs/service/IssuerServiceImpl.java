package com.metrix.awardsmicroservice.libs.service;

import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.exception.IssuerNotFoundException;
import com.metrix.awardsmicroservice.libs.model.Assertion;
import com.metrix.awardsmicroservice.libs.model.Issuer;
import com.metrix.awardsmicroservice.libs.model.RevocationList;
import com.metrix.awardsmicroservice.libs.repository.IAssertionRepository;
import com.metrix.awardsmicroservice.libs.repository.IIssuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class IssuerServiceImpl implements IIssuerService {
    @Autowired
    IIssuerRepository issuerRepository;

    @Autowired
    IAssertionRepository assertionRepository;

    public Issuer saveIssuer(Issuer issuer) throws DuplicateKeyException {
        if (issuerRepository.findByIssuerId(issuer.getIssuerId()) == null) {
            return issuerRepository.save(issuer);
        } else {
            throw new DuplicateKeyException("Issuer already exist with issuer id: " + issuer.getIssuerId());
        }
    }

    public ArrayList<Issuer> findAllIssuer() throws DatabaseEmptyException {
        if (issuerRepository.findAll().size() == 0) {
            throw new DatabaseEmptyException("Database is empty, No Issuer found");
        } else {
            return issuerRepository.findAll();
        }
    }

    public Issuer findByIssuerId(String issuerId) throws IssuerNotFoundException {
        if (issuerRepository.findByIssuerId(issuerId) != null) {
            return issuerRepository.findByIssuerId(issuerId);
        } else {
            throw new IssuerNotFoundException("Issuer not found with assertion id: " + issuerId);
        }
    }

    public void deleteByIssuerId(String issuerId) throws IssuerNotFoundException {
        if (issuerRepository.findByIssuerId(issuerId) != null) {
            issuerRepository.deleteByIssuerId(issuerId);
        } else {
            throw new IssuerNotFoundException("Issuer not found with Issuer id: " + issuerId);
        }
    }

    public Issuer updateRevocationListByIssuerId(String issuerId, String assertionId) {
        Issuer issuerFromDb = issuerRepository.findByIssuerId(issuerId);

        RevocationList revocationList = new RevocationList();
        revocationList.setRevocationListId(UUID.randomUUID().toString());
        revocationList.setIssuer(issuerId);
        revocationList.setType("revocation");
        revocationList.setRevokedAssertions(assertionId);

        ArrayList<RevocationList> prevRevokedAssertion = issuerFromDb.getRevocationList();
        prevRevokedAssertion.add(revocationList);

        issuerFromDb.setRevocationList(prevRevokedAssertion);

        return issuerRepository.save(issuerFromDb);
    }

    public ArrayList<Assertion> getAllRevokedAssertionByIssuerId(String issuerId) {

        ArrayList<Assertion> assertionList = new ArrayList<>();
        ArrayList<RevocationList> revocationLists = issuerRepository.findByIssuerId(issuerId).getRevocationList();
        revocationLists.forEach((revocationList) -> {
            assertionList.add(assertionRepository.findByAssertionId(revocationList.getRevokedAssertions()));
        });

        return assertionList;
    }
}

