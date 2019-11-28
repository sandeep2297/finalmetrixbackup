package com.metrix.activitypipelinemicroservice.repository;

import com.metrix.activitypipelinemicroservice.model.IssuerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface IIssuerProfileRepository extends MongoRepository<IssuerProfile, String> {
    ArrayList<IssuerProfile> findByClientId(String ClientId);

    IssuerProfile findByIssuerProfileId(String issuerProfileId);
}
