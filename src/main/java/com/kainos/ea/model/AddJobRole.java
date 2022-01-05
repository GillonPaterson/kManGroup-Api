package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddJobRole {
    private String jobRole;
    private String jobBandLevel;
    private String jobSpec;
    private String jobLink;
    private String jobResponsibilities;
    private String jobFamily;


    @JsonCreator
    public AddJobRole(@JsonProperty("jobRole") String jobRole, @JsonProperty("jobBandLevelID") String jobBandLevel, @JsonProperty("jobSpec") String jobSpec, @JsonProperty("jobLink") String jobLink, @JsonProperty("jobResponsibilities") String jobResponsibilities, @JsonProperty("jobFamilyID") String jobFamily) {
        this.setJobRole(jobRole);
        this.setJobBandLevel(jobBandLevel);
        this.setJobSpec(jobSpec);
        this.setJobLink(jobLink);
        this.setJobResponsibilities(jobResponsibilities);
        this.setJobFamily(jobFamily);
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getJobBandLevel() {
        return jobBandLevel;
    }

    public void setJobBandLevel(String jobBandLevel) {
        this.jobBandLevel = jobBandLevel;
    }

    public String getJobSpec() {
        return jobSpec;
    }

    public void setJobSpec(String jobSpec) {
        this.jobSpec = jobSpec;
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

    public String getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
    }
}
