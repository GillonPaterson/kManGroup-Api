package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {
    public int jobRoleID;
    public String jobRole;
    public String jobCapability;
    public String jobBandLevel;
    public String jobFamilyName;

    @JsonCreator
    public JobRole(@JsonProperty("jobRoleID") int jobRoleID, @JsonProperty("jobRole") String jobRole, @JsonProperty("jobCapability") String jobCapability, @JsonProperty("jobBandLevel") String jobBandLevel, @JsonProperty("jobFamilyName") String jobFamilyName) {
        this.jobRoleID = jobRoleID;
        this.jobRole = jobRole;
        this.jobCapability = jobCapability;
        this.jobBandLevel = jobBandLevel;
        this.jobFamilyName = jobFamilyName;
    }

    public int getJobRoleID() {
        return jobRoleID;
    }

    public void setJobRoleID(int jobRoleID) {
        this.jobRoleID = jobRoleID;
    }


    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getJobCapability() {
        return jobCapability;
    }

    public void setJobCapability(String jobCapability) {
        this.jobCapability = jobCapability;
    }


    public String getJobBandLevel() {
        return jobBandLevel;
    }

    public void setJobBandLevel(String jobBandLevel) {
        this.jobBandLevel = jobBandLevel;
    }

    public String getJobFamilyName() {
        return jobFamilyName;
    }

    public void setJobFamilyName(String jobFamilyName) {
        this.jobFamilyName = jobFamilyName;
    }
}
