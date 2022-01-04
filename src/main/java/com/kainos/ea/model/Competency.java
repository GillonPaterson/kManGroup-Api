package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Competency {
    private String bandLevel;
    private String competencyStage;

    public Competency(){

    }

    @JsonCreator
    public Competency(@JsonProperty("bandLevel") String bandLevel,@JsonProperty("competencyStage") String competencyStage) {
        this.bandLevel= bandLevel;
        this.competencyStage = competencyStage;

    }

    public String getCompetencyStage() {
        return competencyStage;
    }

    public void setCompetencyStage(String competencyStage) {
        this.competencyStage = competencyStage;
    }

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }
}

