package com.metrix.awardsmicroservice.libs.service;

import com.metrix.awardsmicroservice.libs.exception.BadgeClassNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.BadgeClass;

import java.util.ArrayList;

public interface IBadgeClassService {
    BadgeClass saveBadgeClass(BadgeClass badgeClass);

    ArrayList<BadgeClass> findAllBadgeClass() throws DatabaseEmptyException;

    BadgeClass findByBadgeClassId(String badgeClassId) throws BadgeClassNotFoundException;

    ArrayList<BadgeClass> findBadgeClassByIssuerId(String issuerId) throws BadgeClassNotFoundException;

    void deleteByBadgeClassId(String badgeClassId) throws BadgeClassNotFoundException;
}
