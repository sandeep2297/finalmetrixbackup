package com.metrix.awardsmicroservice.libs.service;


import com.metrix.awardsmicroservice.libs.exception.BadgeNotFoundException;
import com.metrix.awardsmicroservice.libs.exception.DatabaseEmptyException;
import com.metrix.awardsmicroservice.libs.model.Badge;
import com.metrix.awardsmicroservice.libs.repository.IBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class BadgeServiceImpl implements IBadgeService {

    @Autowired
    private IBadgeRepository badgeRepository;

    public Badge saveBadge(Badge badge) {
        badge.setBadgeId(UUID.randomUUID().toString());
        badge.setType("badge");
        return badgeRepository.save(badge);
    }

    public ArrayList<Badge> findAllBadge() throws DatabaseEmptyException {
        if (badgeRepository.findAll().size() == 0) {
            throw new DatabaseEmptyException("Database is empty no badges found");
        } else {
            return badgeRepository.findAll();
        }
    }

    public Badge findByBadgeId(String badgeId) throws BadgeNotFoundException {
        if (badgeRepository.findByBadgeId(badgeId) != null) {
            return badgeRepository.findByBadgeId(badgeId);
        } else {
            throw new BadgeNotFoundException("Badge not found with badge id: " + badgeId);
        }
    }

    public ArrayList<Badge> findBadgesByClientId(String clientId) throws BadgeNotFoundException {
        ArrayList<Badge> listOfBadges;
        if (badgeRepository.findByClientId(clientId) != null) {
            listOfBadges = badgeRepository.findByClientId(clientId);
            return listOfBadges;
        } else {
            throw new BadgeNotFoundException("Badge not found with client id: " + clientId);
        }
    }

    public void deleteByBadgeId(String badgeId) throws BadgeNotFoundException {
        if (badgeRepository.findByBadgeId(badgeId) != null) {
            badgeRepository.deleteByBadgeId(badgeId);
        } else {
            throw new BadgeNotFoundException("Badge not found with badge id: " + badgeId);
        }
    }
    public Badge updateBadge(String badgeId, Badge newBadge) {
        Badge badgeFromDb = badgeRepository.findByBadgeId(badgeId);
        badgeFromDb.setAlignment(newBadge.getAlignment());
//        badgeFromDb.setUpdatedBy();
        badgeFromDb.setUpdatedOn(LocalDateTime.now());
        badgeFromDb.setDescription(newBadge.getDescription());
        badgeFromDb.setCriteria(newBadge.getCriteria());
        badgeFromDb.setImage(newBadge.getImage());
        badgeFromDb.setName(newBadge.getName());
        badgeFromDb.setTags(newBadge.getTags());

        return badgeRepository.save(badgeFromDb);
    }


}