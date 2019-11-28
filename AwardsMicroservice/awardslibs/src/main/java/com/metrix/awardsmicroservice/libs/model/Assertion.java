/**
 * Alignment Model Class
 */
package com.metrix.awardsmicroservice.libs.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Document(collection = "Assertion")
public class Assertion {

    @Id
    String id;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "Assertion Id")
    private String assertionId;
    @ApiModelProperty(notes = "Assertion Type")
    private String type;
    @ApiModelProperty(notes = "Badge Recipient")
    private Recipient recipient;
    @ApiModelProperty(notes = "Badge Id")
    private String badge;
    @ApiModelProperty(notes = "Assertion Verification object")
    private Verification verification;
    @ApiModelProperty(notes = "Issued On Time")
    private LocalDateTime issuedOn;
    @ApiModelProperty(notes = "Badge Image")
    private String image;
    @ApiModelProperty(notes = "Assertion Evidence")
    private ArrayList<Evidence> evidence;
    @ApiModelProperty(notes = "Assertion Narrative that connects multiple pieces of evidence")
    private String assertionNarrative;
    @ApiModelProperty(notes = "Assertion Expires On")
    private LocalDateTime expires;
    @ApiModelProperty(notes = "Assertion Revoked Status")
    private Boolean revoked;
    @ApiModelProperty(notes = "Alignment Revocation Reason")
    private String revocationReason;
    @ApiModelProperty(notes = "Issuer Id")
    private String issuerId;
    @ApiModelProperty(notes = "Assertion Verb")
    private String verb;
    @ApiModelProperty(notes = "Object Content Type")
    private String object;
    @ApiModelProperty(notes = "Target Object Type")
    private String target;
    @ApiModelProperty(notes = "Awarded Badge Name")
    private String badgeName;

    public Assertion() {
    }

    public Assertion(String id, String assertionId, String type, Recipient recipient, String badge, Verification verification, LocalDateTime issuedOn, String image, ArrayList<Evidence> evidence, String assertionNarrative, LocalDateTime expires, Boolean revoked, String revocationReason, String issuerId, String verb, String object, String target, String badgeName) {
        this.id = id;
        this.assertionId = assertionId;
        this.type = type;
        this.recipient = recipient;
        this.badge = badge;
        this.verification = verification;
        this.issuedOn = issuedOn;
        this.image = image;
        this.evidence = evidence;
        this.assertionNarrative = assertionNarrative;
        this.expires = expires;
        this.revoked = revoked;
        this.revocationReason = revocationReason;
        this.issuerId = issuerId;
        this.verb = verb;
        this.object = object;
        this.target = target;
        this.badgeName = badgeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssertionId() {
        return assertionId;
    }

    public void setAssertionId(String assertionId) {
        this.assertionId = assertionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public LocalDateTime getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(LocalDateTime issuedOn) {
        this.issuedOn = issuedOn;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Evidence> getEvidence() {
        return evidence;
    }

    public void setEvidence(ArrayList<Evidence> evidence) {
        this.evidence = evidence;
    }

    public String getAssertionNarrative() {
        return assertionNarrative;
    }

    public void setAssertionNarrative(String assertionNarrative) {
        this.assertionNarrative = assertionNarrative;
    }

    public LocalDateTime getExpires() {
        return expires;
    }

    public void setExpires(LocalDateTime expires) {
        this.expires = expires;
    }

    public Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    public String getRevocationReason() {
        return revocationReason;
    }

    public void setRevocationReason(String revocationReason) {
        this.revocationReason = revocationReason;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    @Override
    public String toString() {
        return "Assertion{" +
                "id='" + id + '\'' +
                ", assertionId='" + assertionId + '\'' +
                ", type='" + type + '\'' +
                ", recipient=" + recipient +
                ", badge='" + badge + '\'' +
                ", verification=" + verification +
                ", issuedOn=" + issuedOn +
                ", image='" + image + '\'' +
                ", evidence=" + evidence +
                ", assertionNarrative='" + assertionNarrative + '\'' +
                ", expires=" + expires +
                ", revoked=" + revoked +
                ", revocationReason='" + revocationReason + '\'' +
                ", issuerId='" + issuerId + '\'' +
                ", verb='" + verb + '\'' +
                ", object='" + object + '\'' +
                ", target='" + target + '\'' +
                ", badgeName='" + badgeName + '\'' +
                '}';
    }

    public boolean isEqual(Assertion assertion) {
        if(this.recipient.getRecipientId().equals(assertion.recipient.getRecipientId())
                        && this.recipient.getRecipientId().equals(assertion.recipient.getRecipientId())
                        && this.badge.equals(assertion.badge)
                        && this.issuedOn.equals(assertion.issuedOn)
                        && this.revoked.equals(assertion.revoked)
                        && this.revocationReason.equals(assertion.revocationReason)
                        && this.issuerId.equals(assertion.issuerId)
                        && this.badgeName.equals(assertion.badgeName)) {
            return true;
        }
        else {
            return false;
        }
    }
}
