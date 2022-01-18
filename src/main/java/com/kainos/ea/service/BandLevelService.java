package com.kainos.ea.service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CompetencyDAO;
import com.kainos.ea.data.TrainingDAO;
import com.kainos.ea.model.BandLevelModel;
import com.kainos.ea.model.CreateBandLevelRequestModel;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.BandLevelValidator;

import javax.validation.ValidationException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BandLevelService {

    BandLevelDAO bandLevelDAO = new BandLevelDAO();
    CompetencyDAO competencyDAO = new CompetencyDAO();
    TrainingDAO trainingDAO = new TrainingDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();
    BandLevelValidator validator = new BandLevelValidator();

    public BandLevelService() {
    }

    public BandLevelService(BandLevelDAO bandLevelDAO, CompetencyDAO competencyDAO, TrainingDAO trainingDAO, DatabaseConnector databaseConnector, BandLevelValidator validator) {
        this.bandLevelDAO = bandLevelDAO;
        this.competencyDAO = competencyDAO;
        this.trainingDAO = trainingDAO;
        this.databaseConnector = databaseConnector;
        this.validator = validator;
    }

    public List<String> getJobBandLevels() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return bandLevelDAO.getBandLevelFromDatabase(connection);
    }

    public List<BandLevelModel> getJobBandLevelsAndImportance() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return bandLevelDAO.getBandLevelAndImportanceFromDatabase(connection);
    }

    public void createBandLevel(CreateBandLevelRequestModel bandLevelInfo) throws SQLException, ValidationException {
        Connection connection = databaseConnector.getConnection();
        int importance = bandLevelInfo.getBandLevel().getImportance();
        int maxImportance = bandLevelDAO.getMaxImportance(connection);

        validator.addBandLevelValidator(bandLevelInfo, maxImportance);

        for (int i = maxImportance; i >= importance; i--) {
            bandLevelDAO.updateImportance(connection, i);
        }

        int bandLevelID = bandLevelDAO.insertBandLevelData(connection, bandLevelInfo.getBandLevel());

        for (int i = 0; i < bandLevelInfo.getCompetencies().length; i++) {
            competencyDAO.insertIntoCompetencies(connection, bandLevelID, bandLevelInfo.getCompetencies()[i]);
        }
        for (int i = 0; i < bandLevelInfo.getTraining().length; i++) {
            trainingDAO.insertIntoBandLevelsTraining(connection, bandLevelID, bandLevelInfo.getTraining()[i]);
        }
    }
}
