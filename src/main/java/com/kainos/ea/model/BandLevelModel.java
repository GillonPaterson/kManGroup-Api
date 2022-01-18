package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandLevelModel {
    public String jobBandLevel;
    public int importance;

    @JsonCreator
    public BandLevelModel(@JsonProperty("jobBandLevel") String jobBandLevel,@JsonProperty("importance") int importance) {
        this.jobBandLevel = jobBandLevel.trim();
        this.importance = importance;
    }

    @JsonProperty("jobBandLevel")
    public String getJobBandLevel() {
        return jobBandLevel;
    }

    public void setJobBandLevel(String jobBandLevel) {
        this.jobBandLevel = jobBandLevel;
    }

    @JsonProperty("importance")
    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
