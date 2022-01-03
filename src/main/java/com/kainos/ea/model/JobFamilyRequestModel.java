package com.kainos.ea.model;

import java.util.List;

public class JobFamilyRequestModel {
    List<String> capabilities;
    List<JobFamilyModel> jobFamilyModels;

    public JobFamilyRequestModel(List<String> capabilities, List<JobFamilyModel> jobFamilyModels) {
        this.capabilities = capabilities;
        this.jobFamilyModels = jobFamilyModels;
    }

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public List<JobFamilyModel> getJobFamilyModels() {
        return jobFamilyModels;
    }

    public void setJobFamilyModels(List<JobFamilyModel> jobFamilyModels) {
        this.jobFamilyModels = jobFamilyModels;
    }
}
