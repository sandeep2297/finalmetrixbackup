package com.metrix.usermicroservice.service;

import com.metrix.usermicroservice.exception.DatabaseEmptyException;
import com.metrix.usermicroservice.model.User;
import org.springframework.dao.DuplicateKeyException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserService {

	void addUserData(User user) throws DuplicateKeyException;

	User loadByUsername(HttpServletRequest request);

	User findByUserEmail(String email);

	List<User> findAllUsers() throws DatabaseEmptyException;

	User patchUserData(String email);

}
