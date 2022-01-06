package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CapabilityRequest {
    private String capabilityName;


    @JsonCreator
    public CapabilityRequest(@JsonProperty("capabiltyName") String capabiltyName) {
        this.capabilityName= capabilityName;

    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }
}
