package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSpecModel {
    public String jobRole;
    public String jobSpec;
    public String jobLink;

    @JsonCreator
    public JobSpecModel(@JsonProperty("jobRole") String jobRole, @JsonProperty("jobSpec") String jobSpec, @JsonProperty("jobLink") String jobLink) {
        this.jobRole = jobRole;
        this.jobSpec = jobSpec;
        this.jobLink = jobLink;
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
}
