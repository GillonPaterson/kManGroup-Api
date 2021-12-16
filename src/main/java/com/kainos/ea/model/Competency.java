package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Competency {
    public String competencyName;
    public String competencyData;

    public Competency(@JsonProperty("competencyName") String competencyName,@JsonProperty("competencyData") String competencyData) {
        this.competencyName = competencyName;
        this.competencyData = competencyData;
    }
}
