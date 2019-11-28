package com.metrix.awardsmicroservice.libs.service;

import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.exception.IssuerNotFoundException;
import com.metrix.awardsmicroservice.libs.model.Issuer;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;

public interface IIssuerService {
    Issuer saveIssuer(Issuer issuer) throws DuplicateKeyException;

    ArrayList<Issuer> findAllIssuer() throws DatabaseEmptyException;

    Issuer findByIssuerId(String issuerId) throws IssuerNotFoundException;

    void deleteByIssuerId(String issuerId) throws IssuerNotFoundException;
}
