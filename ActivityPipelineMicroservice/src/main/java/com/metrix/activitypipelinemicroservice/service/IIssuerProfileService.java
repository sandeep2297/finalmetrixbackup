package com.metrix.activitypipelinemicroservice.service;

import com.metrix.activitypipelinemicroservice.model.IssuerProfile;

import java.util.ArrayList;

public interface IIssuerProfileService {
    IssuerProfile addIssuer(IssuerProfile issuerProfile);

    IssuerProfile findByIdOfIssuer(String issuerId);

    ArrayList<IssuerProfile> findByClientId(String clientId);
}
