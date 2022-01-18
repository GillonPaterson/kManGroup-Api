package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Capabilities {
    private int capabilityID;
    private String capabilityName;


    @JsonCreator
    public Capabilities(@JsonProperty("capabilityID") int capabilityID, @JsonProperty("capabilityName") String capabilityName) {
        this.capabilityID = capabilityID;
        this.capabilityName = capabilityName;

    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }

    public int getCapabilityID() {
        return capabilityID;
    }

    public void setCapabilityID(int capabilityID) {
        this.capabilityID = capabilityID;
    }
}


