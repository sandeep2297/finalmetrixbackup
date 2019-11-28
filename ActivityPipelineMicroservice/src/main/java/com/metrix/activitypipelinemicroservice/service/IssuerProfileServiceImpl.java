package com.metrix.activitypipelinemicroservice.service;

import com.metrix.activitypipelinemicroservice.model.IssuerProfile;
import com.metrix.activitypipelinemicroservice.repository.IIssuerProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IssuerProfileServiceImpl implements IIssuerProfileService {

    @Autowired
    IIssuerProfileRepository issuerProfileRepo;


    // Service to save issuer profile into database
    public IssuerProfile addIssuer(IssuerProfile issuerProfile) throws DuplicateKeyException {
        if (issuerProfileRepo.findByIssuerProfileId(issuerProfile.getIssuerProfileId()) == null) {
            IssuerProfile issuerProfile1 = issuerProfileRepo.save(issuerProfile);
            return issuerProfile1;
        } else {
            throw new DuplicateKeyException("issuer already exists");
        }
    }

    // Service to fetch a particular issuer by issuerId
    public IssuerProfile findByIdOfIssuer(String issuerId) {
        return issuerProfileRepo.findByIssuerProfileId(issuerId);
    }

    // Service to fetch an issuer by clientId
    public ArrayList<IssuerProfile> findByClientId(String clientId) {
        return issuerProfileRepo.findByClientId(clientId);
    }
    public IssuerProfile updateTheIssuer(String issuerId) {
        IssuerProfile issuerFromDb = issuerProfileRepo.findByIssuerProfileId(issuerId);
        return issuerProfileRepo.save(issuerFromDb);
    }

}
