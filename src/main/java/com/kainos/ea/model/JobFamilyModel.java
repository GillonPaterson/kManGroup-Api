package com.kainos.ea.model;

import java.util.List;

public class JobFamilyModel {
    public String jobCapability;
    public List<String> jobFamily;

    public JobFamilyModel(String jobCapability, List<String> jobFamily) {
        this.jobCapability = jobCapability;
        this.jobFamily = jobFamily;
    }

    public JobFamilyModel(String jobCapability) {
        this.jobCapability = jobCapability;
    }

    public String getJobCapability() {
        return jobCapability;
    }

    public void setJobCapability(String jobCapability) {
        this.jobCapability = jobCapability;
    }

    public List<String> getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(List<String> jobFamily) {
        this.jobFamily = jobFamily;
    }
}
