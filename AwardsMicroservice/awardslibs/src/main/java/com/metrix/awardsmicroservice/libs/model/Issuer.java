package com.metrix.awardsmicroservice.libs.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Issuer")
public class Issuer {

    @Id
    String id;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "Issuer Id")
    private String issuerId;
    @ApiModelProperty(notes = "Issuer Type")
    private String type;
    @ApiModelProperty(notes = "Issuer Name")
    private String name;
    @ApiModelProperty(notes = "Issuer URL")
    private String URL;
    @ApiModelProperty(notes = "Issuer Telephone")
    private String telephone;
    @ApiModelProperty(notes = "Issuer Description")
    private String description;
    @ApiModelProperty(notes = "Issuer Image")
    private String image;
    @ApiModelProperty(notes = "Issuer Email")
    private String email;
    @ApiModelProperty(notes = "Issuer Public Key")
    private String publicKey;
    @ApiModelProperty(notes = "Issuer Verification Object")
    private Verification verification;
    @ApiModelProperty(notes = "Revocation List of Issuer")
    private ArrayList<RevocationList> revocationList;

    public Issuer() {
    }

    public Issuer(String id, String issuerId, String type, String name, String URL, String telephone, String description, String image, String email, String publicKey, Verification verification, ArrayList<RevocationList> revocationList) {
        this.id = id;
        this.issuerId = issuerId;
        this.type = type;
        this.name = name;
        this.URL = URL;
        this.telephone = telephone;
        this.description = description;
        this.image = image;
        this.email = email;
        this.publicKey = publicKey;
        this.verification = verification;
        this.revocationList = revocationList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(String issuerId) {
        this.issuerId = issuerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public ArrayList<RevocationList> getRevocationList() {
        return revocationList;
    }

    public void setRevocationList(ArrayList<RevocationList> revocationList) {
        this.revocationList = revocationList;
    }

    @Override
    public String toString() {
        return "Issuer{" +
                "id='" + id + '\'' +
                ", issuerId='" + issuerId + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", URL='" + URL + '\'' +
                ", telephone='" + telephone + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", verification=" + verification +
                ", revocationList=" + revocationList +
                '}';
    }
}