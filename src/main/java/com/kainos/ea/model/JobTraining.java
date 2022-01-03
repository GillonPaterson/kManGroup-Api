package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobTraining {
    private String jobBandLevel;
    private String trainingName;
    private String trainingLink;
    private String trainingGroup;

    @JsonCreator
    public JobTraining(@JsonProperty("jobBandLevel") String JobBandLevel, @JsonProperty("trainingName") String TrainingName, @JsonProperty("trainingLink") String TrainingLink, @JsonProperty("trainingGroup") String TrainingGroup) {
        this.setJobBandLevel(JobBandLevel);
        this.setTrainingName(TrainingName);
        this.setTrainingLink(TrainingLink);
        this.setTrainingGroup(TrainingGroup);
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
