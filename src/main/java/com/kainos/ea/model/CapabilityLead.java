package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CapabilityLead {
    private int leadID;
    private String forename;
    private String surname;
    private String message;
    private String photoUrl;
    private String capability;

    @JsonCreator
    public CapabilityLead(@JsonProperty("leadID") int leadID, @JsonProperty("forename") String forename, @JsonProperty("surname") String  surname, @JsonProperty("message") String message, @JsonProperty("photoUrl") String photoUrl, @JsonProperty("capability") String capability) {
        this.leadID = leadID;
        this.forename = forename;
        this.surname = surname;
        this.message = message;
        this.photoUrl = photoUrl;
        this.capability = capability;

    }

    public int getLeadID() {
        return leadID;
    }

    public void setLeadID(int leadID) {
        this.leadID = leadID;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }
}
