package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSpecModel {
    public String jobRole;
    public String jobSpec;
    public String jobLink;
    public String jobResponsibilities;

    @JsonCreator
    public JobSpecModel(@JsonProperty("jobRole") String jobRole, @JsonProperty("jobSpec") String jobSpec, @JsonProperty("jobLink") String jobLink, @JsonProperty("jobResponsibilities") String jobResponsibilities) {
        this.jobRole = jobRole;
        this.jobSpec = jobSpec;
        this.jobLink = jobLink;
        this.jobResponsibilities = jobResponsibilities;
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
