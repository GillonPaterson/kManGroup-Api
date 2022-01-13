package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EditJobRole {

    private int jobRoleID;
    private String jobRole;
    private String jobSpec;
    private String jobBandLevel;
    private String jobFamily;
    private String jobLink;
    private String jobResponsibilities;


    @JsonCreator
    public EditJobRole(@JsonProperty("jobRoleID") int jobRoleID, @JsonProperty("jobRole") String jobRole, @JsonProperty("jobSpec") String jobSpec, @JsonProperty("jobBandLevel") String jobBandLevel, @JsonProperty("jobFamily") String jobFamily, @JsonProperty("jobLink") String jobLink, @JsonProperty("jobResponsibilities") String jobResponsibilities) {
        this.setJobRoleID(jobRoleID);
        this.setJobRole(jobRole);
        this.setJobSpec(jobSpec);
        this.setJobBandLevel(jobBandLevel);
        this.setJobFamily(jobFamily);
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

    public String getJobSpec() { return jobSpec; }

    public void setJobSpec(String jobSpec) {
        this.jobSpec = jobSpec;
    }

    public String getJobBandLevel() { return jobBandLevel; }

    public void setJobBandLevel(String jobBandLevel) {
        this.jobBandLevel = jobBandLevel;
    }

    public String getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
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
