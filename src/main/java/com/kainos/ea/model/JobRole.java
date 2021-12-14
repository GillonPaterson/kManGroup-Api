package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {
    public int jobRoleID;
    public String jobRole;
    public String jobSpec;

    @JsonCreator
    public JobRole(@JsonProperty("jobRoleID") int jobRoleID, @JsonProperty("jobRole") String jobRole) {
        this.jobRoleID = jobRoleID;
        this.jobRole = jobRole;
    }

    @JsonCreator
    public JobRole(@JsonProperty("jobRoleID") int jobRoleID, @JsonProperty("jobRole") String jobRole, @JsonProperty("jobSpec") String jobSpec) {
        this.jobRoleID = jobRoleID;
        this.jobRole = jobRole;
        this.jobSpec = jobSpec;
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
}
