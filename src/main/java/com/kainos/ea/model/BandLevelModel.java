package com.kainos.ea.model;

public class BandLevelModel {
    public String jobBandLevel;
    public int importance;

    public BandLevelModel(String jobBandLevel, int importance) {
        this.jobBandLevel = jobBandLevel;
        this.importance = importance;
    }

    public String getJobBandLevel() {
        return jobBandLevel;
    }

    public void setJobBandLevel(String jobBandLevel) {
        this.jobBandLevel = jobBandLevel;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
