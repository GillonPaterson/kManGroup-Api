package com.kainos.ea.service;

import com.kainos.ea.data.TrainingDAO;
import com.kainos.ea.model.TrainingAddBandResponseModel;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrainingService {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    TrainingDAO trainingDAO = new TrainingDAO();

    public TrainingService() {
        this.databaseConnector = new DatabaseConnector();
        this.trainingDAO = new TrainingDAO();
    }

    public List<TrainingAddBandResponseModel> getTraining() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        List<TrainingAddBandResponseModel> training = trainingDAO.getTrainingFromDatabase(connection);
        return training;
    }
}
