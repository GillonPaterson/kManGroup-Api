package com.kainos.ea.service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.CapabilityLead;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CapabiltyService {
    JobRolesDAO jobRolesDAO = new JobRolesDAO();
    BandLevelDAO bandLevelDAO = new BandLevelDAO();
    CapabilityDAO capabilityDAO = new CapabilityDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();

    public CapabiltyService(){

    }

    public CapabiltyService(JobRolesDAO jobRolesDAO, DatabaseConnector databaseConnector){
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
    }

    public CapabiltyService(CapabilityDAO capabilityDAO, DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
        this.capabilityDAO = capabilityDAO;
    }

    public CapabiltyService(JobRolesDAO jobRolesDAO, BandLevelDAO bandLevelDAO, CapabilityDAO capabilityDAO, DatabaseConnector databaseConnector){
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
        this.bandLevelDAO = bandLevelDAO;
        this.capabilityDAO = capabilityDAO;
    }

    public List<CapabilityLead> getAllCapabilityLeads() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return capabilityDAO.getAllCapabilityleadsFromDataBase(connection);
    }

    public CapabilityLead getCapabilitylead(int leadID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return capabilityDAO.getCapabilityleadFromDataBase(connection, leadID);
    }


    public Integer createCapability(CapabilityRequest capabilityRequest) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return capabilityDAO.addCapabilityToDatabase(connection, capabilityRequest);
    }
}
