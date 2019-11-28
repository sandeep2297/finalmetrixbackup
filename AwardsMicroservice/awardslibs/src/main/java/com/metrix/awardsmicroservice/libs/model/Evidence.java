package com.metrix.awardsmicroservice.libs.model;

public class Evidence {

    private String evidenceURI;
    private String narrative;
    private String name;
    private String description;
    private String genre;
    private String audience;

    public Evidence() {
    }

    public Evidence(String evidenceURI, String narrative, String name, String description, String genre, String audience) {
        this.evidenceURI = evidenceURI;
        this.narrative = narrative;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.audience = audience;
    }

    public String getEvidenceURI() {
        return evidenceURI;
    }

    public void setEvidenceURI(String evidenceURI) {
        this.evidenceURI = evidenceURI;
    }

    public String getNarrative() {
        return narrative;
    }

    public void setNarrative(String narrative) {
        this.narrative = narrative;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}