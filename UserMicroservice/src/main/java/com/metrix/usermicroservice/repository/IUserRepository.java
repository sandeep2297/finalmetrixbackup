package com.metrix.usermicroservice.repository;

import com.metrix.usermicroservice.model.User;
import org.bson.BSON;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, BSON> {

    User findByEmail(String accountEmail);

    User findByUserName(String user);

}
