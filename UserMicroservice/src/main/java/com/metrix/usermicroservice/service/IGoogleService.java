package com.metrix.usermicroservice.service;

import com.metrix.usermicroservice.model.User;

public interface IGoogleService {

	String googlelogin();

	String getGoogleAccessToken(String code);

	User getGoogleUserProfile(String accessToken);

}
