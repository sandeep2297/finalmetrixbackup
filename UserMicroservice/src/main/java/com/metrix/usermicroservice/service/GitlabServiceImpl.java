package com.metrix.usermicroservice.service;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.metrix.usermicroservice.model.GitlabGroup;
import com.metrix.usermicroservice.repository.IUserRepository;

@Service
public class GitlabServiceImpl implements IGitlabService {

	@Value("${gitlab.base.url}")
	private String baseUrl;

	@Value("${spring.security.oauth2.client.registration.gitlab.client-id}")
	String clientId;

	@Value("${spring.security.oauth2.client.registration.gitlab.client-secret}")
	String clientSecret;

	@Value("${spring.security.oauth2.client.registration.gitlab.redirect-uri}")
	String redirectUrl;

	@Value("${Token_Url}")
	String tokenurl;

	@Value("${User_Url}")
	String userurl;

	@Value("${Groups_Url}")
	String groupsurl;

	@Value("${User_Group}")
	String usergroup;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	IUserRepository userRepo;

	@Autowired
	IUserService userService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// Creates Gitlab OAuth Connection
	public String getAccessToken(String code) {
		// System.out.println("in getAccesstoken method");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(tokenurl)
				.queryParam("client_id", this.clientId).queryParam("client_secret", this.clientSecret)
				.queryParam("code", code).queryParam("grant_type", "authorization_code")
				.queryParam("redirect_uri", this.redirectUrl);
		HttpEntity<String> request = new HttpEntity<>(headers);
		ResponseEntity<JSONObject> result = this.restTemplate.postForEntity(builder.toUriString(), request,
				JSONObject.class);
		// System.out.println("Access Token: " + result.getBody().get("access_token"));
		return (String) result.getBody().get("access_token");
	}

	// For getting gitlab user profile
	public JSONObject getUserProfile(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(userurl).queryParam("access_token",
				accessToken);
		ResponseEntity<JSONObject> result = this.restTemplate.getForEntity(builder.toUriString(), JSONObject.class);
		logger.info("User Profile: " + result);
		// System.out.println("User Profile: " + result);
		JSONObject userDetails = result.getBody();
		return userDetails;
	}

	// Get user groups from gitlab
	public boolean getUserGroups(String accessToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(groupsurl).queryParam("access_token",
				accessToken);
		GitlabGroup[] result = this.restTemplate.getForObject(builder.toUriString(), GitlabGroup[].class);
		if (result.length == 0) {
			return false;
		} else {
			for (int i = 0; i < result.length; i++) {
				if (result[i].getName().equals(usergroup)) {
					return true;
				}
			}
			return false;
		}

	}

}
