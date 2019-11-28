package com.metrix.awardsmicroservice.libs.repository;

import com.metrix.awardsmicroservice.libs.model.BadgeClass;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IBadgeClassRepository extends MongoRepository<BadgeClass, String> {

    ArrayList<BadgeClass> findAll();

    BadgeClass findByBadgeClassId(String badgeClassId);

    ArrayList<BadgeClass> findByIssuer(String issuer);

    void deleteByBadgeClassId(String badgeClassId);
}
