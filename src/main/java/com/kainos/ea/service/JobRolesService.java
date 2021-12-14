package com.kainos.ea.service;

import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobRolesService {

    JobRolesDAO jobRolesDAO = new JobRolesDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobRolesService(){

    }

    public JobRolesService(JobRolesDAO jobRolesDAO, DatabaseConnector databaseConnector){
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
    }

    public List<JobRole> getJobRoles() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobRolesFromDatabase(connection);
    }

    public List<JobRole> getJobSpec(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobSpecFromDatabase(connection, jobRoleID);
    }
}