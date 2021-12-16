package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Competency {
    public String competencyName;
    public String competencyData;
    private String bandLevel;
    private String competencyStage1;
    private String competencyStage2;
    private String competencyStage3;
    private String competencyStage4;
    private String competencyStage;

    public Competency(){

    }

    @JsonCreator
    public Competency(@JsonProperty("bandLevel") String bandLevel, @JsonProperty("competencyStage1") String competencyStage1, @JsonProperty("competencyStage2") String competencyStage2, @JsonProperty("competencyStage3") String competencyStage3, @JsonProperty("competencyStage4") String competencyStage4,@JsonProperty("competencyStage") String competencyStage) {
        this.bandLevel = bandLevel;
        this.competencyStage1 = competencyStage1;
        this.competencyStage2 = competencyStage2;
        this.competencyStage3 = competencyStage3;
        this.competencyStage4 = competencyStage4;
        this.competencyStage = competencyStage;

    }

    @JsonCreator
    public Competency(@JsonProperty("bandLevel") String bandLevel, @JsonProperty("competencyStage1") String competencyStage1){
        this.bandLevel = bandLevel;
        this.competencyStage1 = competencyStage1;
    }

    @JsonCreator
    public Competency(@JsonProperty("bandLevel") String bandLevel, @JsonProperty("competencyStage1") String competencyStage1, @JsonProperty("competencyStage2") String competencyStage2){
        this.bandLevel = bandLevel;
        this.competencyStage1 = competencyStage1;
        this.competencyStage2 = competencyStage2;
    }

    @JsonCreator
    public Competency(@JsonProperty("bandLevel") String bandLevel, @JsonProperty("competencyStage1") String competencyStage1, @JsonProperty("competencyStage2") String competencyStage2, @JsonProperty("competencyStage3") String competencyStage3){
        this.bandLevel = bandLevel;
        this.competencyStage1 = competencyStage1;
        this.competencyStage2 = competencyStage2;
        this.competencyStage3 = competencyStage3;
    }

    @JsonCreator
    public Competency(@JsonProperty("bandLevel") String bandLevel, @JsonProperty("competencyStage1") String competencyStage1, @JsonProperty("competencyStage2") String competencyStage2, @JsonProperty("competencyStage3") String competencyStage3, @JsonProperty("competencyStage4") String competencyStage4){
        this.bandLevel = bandLevel;
        this.competencyStage1 = competencyStage1;
        this.competencyStage2 = competencyStage2;
        this.competencyStage3 = competencyStage3;
        this.competencyStage4 = competencyStage4;

    }

    public String getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(String bandLevel) {
        this.bandLevel = bandLevel;
    }

    public String getCompetencyStage1() {
        return competencyStage1;
    }

    public void setCompetencyStage1(String competencyStage1) {
        this.competencyStage1 = competencyStage1;
    }

    public String getCompetencyStage2() {
        return competencyStage2;
    }

    public void setCompetencyStage2(String competencyStage2) {
        this.competencyStage2 = competencyStage2;
    }

    public String getCompetencyStage3() {
        return competencyStage3;
    }

    public void setCompetencyStage3(String competencyStage3) {
        this.competencyStage3 = competencyStage3;
    }

    public String getCompetencyStage4() {
        return competencyStage4;
    }

    public void setCompetencyStage4(String competencyStage4) {
        this.competencyStage4 = competencyStage4;
    }

    public String getCompetencyStage() {
        return competencyStage;
    }

    public void setCompetencyStage(String competencyStage) {
        this.competencyStage = competencyStage;
    }
}

