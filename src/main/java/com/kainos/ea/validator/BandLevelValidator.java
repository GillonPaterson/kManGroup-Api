package com.kainos.ea.validator;

import com.kainos.ea.data.CompetencyDAO;
import com.kainos.ea.data.TrainingDAO;
import com.kainos.ea.model.CreateBandLevelRequestModel;
import com.kainos.ea.util.DatabaseConnector;

import javax.validation.ValidationException;
import java.sql.Connection;
import java.sql.SQLException;

public class BandLevelValidator {
    TrainingDAO trainingDAO = new TrainingDAO();
    CompetencyDAO competencyDAO = new CompetencyDAO();
    DatabaseConnector connector = new DatabaseConnector();

    public BandLevelValidator() {
    }

    public BandLevelValidator(TrainingDAO trainingDAO, CompetencyDAO competencyDAO, DatabaseConnector connector) {
        this.trainingDAO = trainingDAO;
        this.competencyDAO = competencyDAO;
        this.connector = connector;
    }

    public void addBandLevelValidator(CreateBandLevelRequestModel bandLevelInfo, int maxImportance) throws SQLException {
        String bandLevel = bandLevelInfo.getBandLevel().jobBandLevel;
        if (bandLevelInfo.getBandLevel().getImportance() < 1 || bandLevelInfo.getBandLevel().getImportance() > maxImportance + 1) {
            throw new ValidationException("Importance too high or low");
        }
        if (!bandLevel.matches("^[a-zA-Z\\s]+$")) {
            throw new ValidationException("Band Level must only contain letters and spaces");
        }
        if (bandLevelInfo.getTraining().length < 1 || bandLevelInfo.getTraining().length > 6) {
            throw new ValidationException("Training must have at least one item in it and no greater than 6");
        }
        if (bandLevelInfo.getCompetencies().length < 1 || bandLevelInfo.getCompetencies().length > 6) {
            throw new ValidationException("Competencies must have at least one item in it and no greater than 6");
        }
        Connection connection = connector.getConnection();
        if (!trainingDAO.checkTrainingID(connection, bandLevelInfo.getTraining())) {
            throw new ValidationException("Some Training id's don't exist");
        }
        if (!competencyDAO.checkCompetencyID(connection, bandLevelInfo.getCompetencies())) {
            throw new ValidationException("Some Competency id's don't exist");
        }
    }
}
