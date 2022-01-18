package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CapabilityName {
    private String capabilityname;

    @JsonCreator
    public CapabilityName(@JsonProperty("capabilityName") String capabilityName) {
        this.capabilityname = capabilityName;

    }

    public String getCapabilityname() {
        return capabilityname;
    }

    public void setCapabilityname(String capabilityname) {
        this.capabilityname = capabilityname;
    }
}
