package com.metrix.usermicroservice.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "ClientProfile")
public class ClientProfile {
	@Id
	private String id;
	@Indexed(unique=true)
	@ApiModelProperty(notes = "Client Profile Name")
	private String profileName;
	@ApiModelProperty(notes = "Owned By")
	private String ownedBy;
	@Indexed(unique = true)
	@ApiModelProperty(notes = "Client Email")
	private String clientEmail;
	@ApiModelProperty(notes = "Client Avatar URL")
	private String avatarURL;
	@ApiModelProperty(notes = "Client Organisation Name")
	private String organisationName;
	@ApiModelProperty(notes = "Organization Email")
	private String orgEmail;
	@ApiModelProperty(notes = "Client Organisation URL")
	private String organisationURL;
	@ApiModelProperty(notes = "Metadata reference")
	private Metadata metadata;
	@ApiModelProperty(notes = "Collaborators")
	private ArrayList<String> collaborators;
	@ApiModelProperty(notes = "Created At")
	private LocalDateTime createdAt;
	@ApiModelProperty(notes = "Created By")
	private String createdBy;
	@ApiModelProperty(notes = "Updated At")
	private LocalDateTime updatedAt;
	@ApiModelProperty(notes = "Updated By")
	private String updatedBy;
	@ApiModelProperty(notes = "Client Profile Description")
	private String description;
	@ApiModelProperty(notes = "Client Organisation Description")
	private String aboutOrg;

	public ClientProfile() {
	}

	public ClientProfile(String id, String profileName, String ownedBy, String clientEmail, String avatarURL,
			String organisationName, String orgEmail, String organisationURL, Metadata metadata,
			ArrayList<String> collaborators, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt,
			String updatedBy, String description, String aboutOrg) {
		super();
		this.id = id;
		this.profileName = profileName;
		this.ownedBy = ownedBy;
		this.clientEmail = clientEmail;
		this.avatarURL = avatarURL;
		this.organisationName = organisationName;
		this.orgEmail = orgEmail;
		this.organisationURL = organisationURL;
		this.metadata = metadata;
		this.collaborators = collaborators;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
		this.description = description;
		this.aboutOrg = aboutOrg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getOrganisationURL() {
		return organisationURL;
	}

	public void setOrganisationURL(String organisationURL) {
		this.organisationURL = organisationURL;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public ArrayList<String> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(ArrayList<String> collaborators) {
		this.collaborators = collaborators;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAboutOrg() {
		return aboutOrg;
	}

	public void setAboutOrg(String aboutOrg) {
		this.aboutOrg = aboutOrg;
	}

}
