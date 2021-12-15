package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {
    public int jobRoleID;
    public String jobRole;
    public String jobCapability;
    public String jobBandLevel;
    public String jobSpec;
    public String competencyData;

    @JsonCreator
    public JobRole(@JsonProperty("jobRoleID") int jobRoleID, @JsonProperty("jobRole") String jobRole, @JsonProperty("jobCapability") String jobCapability, @JsonProperty("jobBandLevel") String jobBandLevel) {
        this.jobRoleID = jobRoleID;
        this.jobRole = jobRole;
        this.jobCapability = jobCapability;
        this.jobBandLevel = jobBandLevel;
    }

    @JsonCreator
    public JobRole(@JsonProperty("jobRoleID") int jobRoleID, @JsonProperty("jobRole") String jobRole, @JsonProperty("jobCapability") String jobCapability, @JsonProperty("jobBandLevel") String jobBandLevel, @JsonProperty("jobSpec") String jobSpec) {
        this.jobRoleID = jobRoleID;
        this.jobRole = jobRole;
        this.jobCapability = jobCapability;
        this.jobBandLevel = jobBandLevel;
        this.jobSpec = jobSpec;
    }

    @JsonCreator
    public JobRole(@JsonProperty("jobBandLevel") String jobBandLevel,@JsonProperty("competencyData") String competencyData){
        this.jobBandLevel = jobBandLevel;
        this.competencyData = competencyData;
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


    public String getJobSpec() {
        return jobSpec;
    }

    public void setJobSpec(String jobSpec) {
        this.jobSpec = jobSpec;
    }


    public String getJobCapability() {
        return jobCapability;
    }

    public void setJobCapability(String jobCapability) { this.jobCapability = jobCapability; }


    public String getJobBandLevel() {
        return jobBandLevel;
    }

    public void setJobBandLevel(String jobBandLevel) { this.jobBandLevel = jobBandLevel; }
}
