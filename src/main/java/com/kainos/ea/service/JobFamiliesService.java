package com.kainos.ea.service;

import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobFamiliesDAO;
import com.kainos.ea.model.JobFamilyModel;
import com.kainos.ea.model.JobFamilyRequestModel;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobFamiliesService {
    JobFamiliesDAO jobFamiliesDAO = new JobFamiliesDAO();
    CapabilityDAO capabilityDAO = new CapabilityDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public JobFamiliesService(){

    }

    public JobFamiliesService(JobFamiliesDAO jobFamiliesDAO, CapabilityDAO capabilityDAO, DatabaseConnector databaseConnector) {
        this.jobFamiliesDAO = jobFamiliesDAO;
        this.capabilityDAO = capabilityDAO;
        this.databaseConnector = databaseConnector;
    }

    public JobFamilyRequestModel getJobFamilies() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        List<String> capabilities = capabilityDAO.getJobCapabilitiesFromDatabase(connection);

        List<JobFamilyModel> jobFamilyModels = jobFamiliesDAO.getFamilies(connection);

        JobFamilyRequestModel jobFamilyRequestModel = new JobFamilyRequestModel(capabilities, jobFamilyModels);
        return jobFamilyRequestModel;
    }
}
