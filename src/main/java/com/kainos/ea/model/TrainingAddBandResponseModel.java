package com.kainos.ea.model;

public class TrainingAddBandResponseModel {
    public int trainingId;
    public String trainingName;
    public String trainingLink;

    public TrainingAddBandResponseModel(int trainingId, String trainingName, String trainingLink) {
        this.trainingId = trainingId;
        this.trainingName = trainingName;
        this.trainingLink = trainingLink;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingLink() {
        return trainingLink;
    }

    public void setTrainingLink(String trainingLink) {
        this.trainingLink = trainingLink;
    }
}
