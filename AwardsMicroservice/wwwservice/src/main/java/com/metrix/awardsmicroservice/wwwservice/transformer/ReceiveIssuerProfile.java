package com.metrix.awardsmicroservice.wwwservice.transformer;


import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.exception.IssuerNotFoundException;
import com.metrix.awardsmicroservice.libs.model.Issuer;
import com.metrix.awardsmicroservice.libs.model.IssuerProfile;
import com.metrix.awardsmicroservice.libs.service.IssuerServiceImpl;
import com.metrix.awardsmicroservice.wwwservice.controller.IssuerFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ReceiveIssuerProfile{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IssuerFeignClient proxy;

    @Autowired
    IssuerServiceImpl issuerService;

    ArrayList<Issuer> issuerArrayList;

    public ArrayList<Issuer> saveIssuerList(String clientId) throws IssuerNotFoundException {
        ArrayList<IssuerProfile> issuerProfileArrayList = proxy.getByClientId(clientId);
        issuerArrayList = new ArrayList<>();
        for ( int i = 0; i<issuerProfileArrayList.size(); i++) {
            Issuer issuer = new Issuer();
            IssuerProfile issuerProfile = issuerProfileArrayList.get(i);
            issuer.setIssuerId(issuerProfile.getIssuerProfileId());
            issuer.setType("issuer");
            issuer.setName(issuerProfile.getIssuerName());
            issuer.setURL(issuerProfile.getIssuerOrganizationURL());
            issuer.setDescription(issuerProfile.getIssuerDescription());
            issuer.setImage(issuerProfile.getIssuerAvatarURL());
            issuer.setEmail(issuerProfile.getIssuerEmail());
            issuerArrayList.add(issuer);
        }
        return issuerArrayList;
    }

    public void saveIssuer() {
        for ( Issuer issuer: issuerArrayList) {
            try {
                issuerService.saveIssuer(issuer);
            } catch ( DuplicateKeyException exception) {
                logger.error("Exception while saving issuer: " + exception);
                continue;
            }
        }

    }
}
