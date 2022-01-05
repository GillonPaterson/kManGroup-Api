package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Competency {
    private String bandLevel;
    private List<String> competencyStage;

    public Competency(){

    }

    @JsonCreator
    public Competency(@JsonProperty("bandLevel") String bandLevel,@JsonProperty("competencyStage") List competencyStage) {
        this.bandLevel= bandLevel;
        this.competencyStage = competencyStage;

    }

    public List getCompetencyStage() {
        return competencyStage;
    }

    public void setCompetencyStage(List competencyStage) {
        this.competencyStage = competencyStage;
    }

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }
}

