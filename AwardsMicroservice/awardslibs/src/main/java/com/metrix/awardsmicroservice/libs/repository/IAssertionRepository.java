package com.metrix.awardsmicroservice.libs.repository;

import com.metrix.awardsmicroservice.libs.model.Assertion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IAssertionRepository extends MongoRepository<Assertion, String> {

    ArrayList<Assertion> findByBadge(String badge);

    ArrayList<Assertion> findByIssuerId(String issuerId);

    ArrayList<Assertion> findAll();

    Assertion findByAssertionId(String assertionId);

    void deleteByAssertionId(String assertionId);
}
