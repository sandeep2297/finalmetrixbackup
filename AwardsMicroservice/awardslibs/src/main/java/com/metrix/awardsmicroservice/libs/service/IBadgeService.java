package com.metrix.awardsmicroservice.libs.service;

import com.metrix.awardsmicroservice.libs.exception.BadgeNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.Badge;

import java.util.ArrayList;

public interface IBadgeService {

    Badge saveBadge(Badge badge);

    ArrayList<Badge> findAllBadge() throws DatabaseEmptyException;

    Badge findByBadgeId(String badgeId) throws BadgeNotFoundException;

    ArrayList<Badge> findBadgesByClientId(String clientId) throws BadgeNotFoundException;

    void deleteByBadgeId(String badgeId) throws BadgeNotFoundException;
}