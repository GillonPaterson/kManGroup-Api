package com.kainos.ea.model;

public class JobFamilyModel {
    public String jobCapability;
    public String jobFamily;

    public JobFamilyModel(String jobCapability, String jobFamily) {
        this.jobCapability = jobCapability;
        this.jobFamily = jobFamily;
    }

    public String getJobCapability() {
        return jobCapability;
    }

    public void setJobCapability(String jobCapability) {
        this.jobCapability = jobCapability;
    }

    public String getJobFamily() {
        return jobFamily;
    }

    public void setJobFamily(String jobFamily) {
        this.jobFamily = jobFamily;
    }
}
