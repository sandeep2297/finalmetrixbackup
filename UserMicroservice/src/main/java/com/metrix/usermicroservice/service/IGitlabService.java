package com.metrix.usermicroservice.service;

import org.json.simple.JSONObject;

public interface IGitlabService {

	String getAccessToken(String code);

	JSONObject getUserProfile(String accessToken);

	boolean getUserGroups(String accessToken);
}
