package com.metrix.awardsmicroservice.libs.repository;

import com.metrix.awardsmicroservice.libs.model.Badge;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IBadgeRepository extends MongoRepository<Badge, String> {

    ArrayList<Badge> findAll();

    Badge findByBadgeId(String badgeId);

    ArrayList<Badge> findByClientId(String clientId);

    void deleteByBadgeId(String badgeId);

}