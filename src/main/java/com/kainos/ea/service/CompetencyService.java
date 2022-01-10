package com.kainos.ea.service;

import com.kainos.ea.data.CompetencyDAO;
import com.kainos.ea.model.Competency;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class CompetencyService {
    CompetencyDAO competencyDAO = new CompetencyDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public CompetencyService(){

    }

    public CompetencyService(CompetencyDAO competencyDAO, DatabaseConnector databaseConnector ){
        this.competencyDAO = competencyDAO;
        this.databaseConnector = databaseConnector;

    }


    public Competency getComp(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return competencyDAO.getJobCompFromDatabase(connection,jobRoleID);
    }

}
