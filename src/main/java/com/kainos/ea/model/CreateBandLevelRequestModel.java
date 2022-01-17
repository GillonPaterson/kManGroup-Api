package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBandLevelRequestModel {
    private BandLevelModel bandLevel;
    private int[] training;
    private int[] competencies;

    @JsonCreator
    public CreateBandLevelRequestModel(@JsonProperty("bandLevel") BandLevelModel bandLevel,@JsonProperty("training") int[] training,@JsonProperty("competencies") int[] competencies) {
        this.bandLevel = bandLevel;
        this.training = training;
        this.competencies = competencies;
    }

    @JsonProperty("bandLevel")
    public BandLevelModel getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(BandLevelModel bandLevel) {
        this.bandLevel = bandLevel;
    }

    @JsonProperty("training")
    public int[] getTraining() {
        return training;
    }

    public void setTraining(int[] training) {
        this.training = training;
    }

    @JsonProperty("competencies")
    public int[] getCompetencies() {
        return competencies;
    }

    public void setCompetencies(int[] competencies) {
        this.competencies = competencies;
    }
}
