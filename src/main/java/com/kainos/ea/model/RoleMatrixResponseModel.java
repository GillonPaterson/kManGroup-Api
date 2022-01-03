package com.kainos.ea.model;

import java.util.List;

public class RoleMatrixResponseModel {
    public List<RoleMatrixModel> roleMatrixModel;
    public List<String> bandLevel;
    public List<String> capability;

    public RoleMatrixResponseModel(List<RoleMatrixModel> roleMatrixModel, List<String> bandLevel, List<String> capability) {
        this.roleMatrixModel = roleMatrixModel;
        this.bandLevel = bandLevel;
        this.capability = capability;
    }

    public List<RoleMatrixModel> getRoleMatrixModel() {
        return roleMatrixModel;
    }

    public void setRoleMatrixModel(List<RoleMatrixModel> roleMatrixModel) {
        this.roleMatrixModel = roleMatrixModel;
    }

    public List<String> getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(List<String> bandLevel) {
        this.bandLevel = bandLevel;
    }

    public List<String> getCapability() {
        return capability;
    }

    public void setCapability(List<String> capability) {
        this.capability = capability;
    }
}
