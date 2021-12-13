package com.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {
    public int employeeID;
    public String employeeRole;

    @JsonCreator
    public JobRole(@JsonProperty("employeeID") int employeeID, @JsonProperty("employeeRole") String employeeRole) {
        this.employeeID = employeeID;
        this.employeeRole = employeeRole;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}
