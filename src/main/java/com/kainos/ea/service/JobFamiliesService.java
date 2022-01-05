package com.kainos.ea.service;

import com.kainos.ea.data.JobFamiliesDAO;
import com.kainos.ea.model.JobFamilyModel;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobFamiliesService {
    JobFamiliesDAO jobFamiliesDAO = new JobFamiliesDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobFamiliesService(){

    }

    public JobFamiliesService(JobFamiliesDAO jobFamiliesDAO, DatabaseConnector databaseConnector) {
        this.jobFamiliesDAO = jobFamiliesDAO;
        this.databaseConnector = databaseConnector;
    }

    public List<JobFamilyModel> getJobFamilies() throws SQLException {
        Connection connection = databaseConnector.getConnection();

        List<JobFamilyModel> jobFamilyModels = jobFamiliesDAO.getFamilies(connection);

        return jobFamilyModels;
    }
}
