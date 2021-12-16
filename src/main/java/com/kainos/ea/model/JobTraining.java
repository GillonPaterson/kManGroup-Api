package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobTraining {
    private String jobBandLevel;
    private String trainingLink;

    @JsonCreator
    public JobTraining(@JsonProperty("jobBandLevel") String JobBandLevel, @JsonProperty("trainingLink") String TrainingLink) {
        this.setJobBandLevel(JobBandLevel);
        this.setTrainingLink(TrainingLink);
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
}