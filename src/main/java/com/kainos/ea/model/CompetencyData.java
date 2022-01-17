package com.kainos.ea.model;

public class CompetencyData {
    public int competencyDataID;
    public String competencyStage;

    public CompetencyData(int competencyDataID, String competencyStage) {
        this.competencyDataID = competencyDataID;
        this.competencyStage = competencyStage;
    }

    public int getCompetencyDataID() {
        return competencyDataID;
    }

    public void setCompetencyDataID(int competencyDataID) {
        this.competencyDataID = competencyDataID;
    }

    public String getCompetencyStage() {
        return competencyStage;
    }

    public void setCompetencyStage(String competencyStage) {
        this.competencyStage = competencyStage;
    }
}
