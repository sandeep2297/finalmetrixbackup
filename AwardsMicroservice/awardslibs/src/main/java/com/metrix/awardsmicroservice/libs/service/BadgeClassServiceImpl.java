package com.metrix.awardsmicroservice.libs.service;

import com.metrix.awardsmicroservice.libs.exception.BadgeClassNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.BadgeClass;
import com.metrix.awardsmicroservice.libs.repository.IBadgeClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class BadgeClassServiceImpl implements IBadgeClassService{

    @Autowired
    private IBadgeClassRepository badgeClassRepository;

    public BadgeClass saveBadgeClass(BadgeClass badgeClass) {
        badgeClass.setBadgeClassId(UUID.randomUUID().toString());
        badgeClass.setType("badgeClass");
        return badgeClassRepository.save(badgeClass);
    }

    public ArrayList<BadgeClass> findAllBadgeClass() throws DatabaseEmptyException {
        if (badgeClassRepository.findAll().size() == 0) {
            throw new DatabaseEmptyException("Database is empty no badge instances found");
        } else {
            return badgeClassRepository.findAll();
        }
    }

    public BadgeClass findByBadgeClassId(String badgeClassId) throws BadgeClassNotFoundException {
        if (badgeClassRepository.findByBadgeClassId(badgeClassId) != null) {
            return badgeClassRepository.findByBadgeClassId(badgeClassId);
        } else {
            throw new BadgeClassNotFoundException("Badge Instance not found with badge instance id: "+badgeClassId);
        }
    }

    public ArrayList<BadgeClass> findBadgeClassByIssuerId(String issuerId) throws BadgeClassNotFoundException {
        ArrayList<BadgeClass> badgeClassArrayList;
        if (badgeClassRepository.findByIssuer(issuerId) != null) {
            badgeClassArrayList = badgeClassRepository.findByIssuer(issuerId);
            return badgeClassArrayList;
        } else {
            throw new BadgeClassNotFoundException("No Badge Instances found with issuer id: "+ issuerId);
        }
    }

    public void deleteByBadgeClassId(String badgeClassId) throws BadgeClassNotFoundException {
        if (badgeClassRepository.findByBadgeClassId(badgeClassId) != null) {
            badgeClassRepository.deleteByBadgeClassId(badgeClassId);
        } else {
            throw new BadgeClassNotFoundException("Badge Instance not found with badge instance id: "+badgeClassId);
        }
    }
}
