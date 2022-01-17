package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobTraining {
    private String jobBandLevel;
    private String trainingName;
    private String trainingLink;
    private String trainingGroup;

    @JsonCreator
    public JobTraining(@JsonProperty("jobBandLevel") String jobBandLevel, @JsonProperty("trainingName") String trainingName, @JsonProperty("trainingLink") String trainingLink, @JsonProperty("trainingGroup") String trainingGroup) {
        this.setJobBandLevel(jobBandLevel);
        this.setTrainingName(trainingName);
        this.setTrainingLink(trainingLink);
        this.setTrainingGroup(trainingGroup);
    }

    public String getJobBandLevel() {
        return jobBandLevel;
    }

    public void setJobBandLevel(String jobBandLevel) {
        this.jobBandLevel = jobBandLevel;
    }

    public String getTrainingLink() {
        return trainingLink;
    }

    public void setTrainingLink(String trainingLink) {
        this.trainingLink = trainingLink;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingGroup() {
        return trainingGroup;
    }

    public void setTrainingGroup(String trainingGroup) {
        this.trainingGroup = trainingGroup;
    }
}
