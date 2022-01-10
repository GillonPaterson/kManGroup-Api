package com.kainos.ea.service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BandLevelService {

    BandLevelDAO bandLevelDAO = new BandLevelDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public List<String> getJobBandLevels() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return bandLevelDAO.getBandLevelFromDatabase(connection);
    }
}
