package com.kainos.ea.model;

public class RoleMatrixModel {
    public String jobRole;
    public String capability;
    public String bandLevel;

    public RoleMatrixModel(String jobRole, String capability, String bandLevel) {
        this.jobRole = jobRole;
        this.capability = capability;
        this.bandLevel = bandLevel;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getCapability() {
        return capability;
    }

    public void setCapability(String capability) {
        this.capability = capability;
    }

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }
}
