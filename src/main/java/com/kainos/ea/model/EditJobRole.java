package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditJobRole {

    private int jobRoleID;
    private String jobRole;
    private String jobSpec;
    private int jobBandLevelID;
    private int jobFamilyID;
    private String jobLink;
    private String jobResponsibilities;


    @JsonCreator
    public EditJobRole(@JsonProperty("jobRoleID") int jobRoleID, @JsonProperty("jobRole") String jobRole, @JsonProperty("jobSpec") String jobSpec, @JsonProperty("jobBandLevelID") int jobBandLevelID, @JsonProperty("jobFamilyID") int jobFamilyID, @JsonProperty("jobLink") String jobLink, @JsonProperty("jobResponsibilities") String jobResponsibilities) {
        this.setJobRoleID(jobRoleID);
        this.setJobRole(jobRole);
        this.setJobSpec(jobSpec);
        this.setJobBandLevelID(jobBandLevelID);
        this.setJobFamilyID(jobFamilyID);
        this.setJobLink(jobLink);
        this.setJobResponsibilities(jobResponsibilities);
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

    public int getJobBandLevelID() {
        return jobBandLevelID;
    }

    public void setJobBandLevelID(int jobBandLevelID) {
        this.jobBandLevelID = jobBandLevelID;
    }

    public int getJobFamilyID() {
        return jobFamilyID;
    }

    public void setJobFamilyID(int jobFamilyID) {
        this.jobFamilyID = jobFamilyID;
    }

    public String getJobLink() {
        return jobLink;
    }

    public void setJobLink(String jobLink) {
        this.jobLink = jobLink;
    }

    public String getJobResponsibilities() {
        return jobResponsibilities;
    }

    public void setJobResponsibilities(String jobResponsibilities) {
        this.jobResponsibilities = jobResponsibilities;
    }
}
