package com.metrix.awardsmicroservice.libs.repository;

import com.metrix.awardsmicroservice.libs.model.Issuer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface IIssuerRepository extends MongoRepository<Issuer, String> {

    ArrayList<Issuer> findAll();

    Issuer findByIssuerId(String issuerId);

    void deleteByIssuerId(String issuerId);
}
