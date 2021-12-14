package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {
    public int jobRoleID;
    public String jobRole;

    @JsonCreator
    public JobRole(@JsonProperty("jobRoleID") int employeeID, @JsonProperty("jobRole") String employeeRole) {
        this.jobRoleID = jobRoleID;
        this.jobRole = jobRole;
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
}
