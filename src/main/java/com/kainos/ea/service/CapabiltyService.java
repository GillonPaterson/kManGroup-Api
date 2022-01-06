package com.kainos.ea.service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.CapabilityLead;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.CapabilityValidator;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CapabiltyService {
    JobRolesDAO jobRolesDAO = new JobRolesDAO();
    BandLevelDAO bandLevelDAO = new BandLevelDAO();
    CapabilityDAO capabilityDAO = new CapabilityDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();
    CapabilityValidator capabilityValidator = new CapabilityValidator();

    public CapabiltyService(){

    }

    public CapabiltyService(CapabilityValidator capabilityValidator){
        this.capabilityValidator = capabilityValidator;

    }

    public CapabiltyService(JobRolesDAO jobRolesDAO, DatabaseConnector databaseConnector){
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
    }

    public CapabiltyService(CapabilityDAO capabilityDAO, DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
        this.capabilityDAO = capabilityDAO;
    }

    public CapabiltyService(CapabilityDAO capabilityDAO, DatabaseConnector databaseConnector,CapabilityValidator capabilityValidator){
        this.databaseConnector = databaseConnector;
        this.capabilityDAO = capabilityDAO;
        this.capabilityValidator = capabilityValidator;

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
        String var = capabilityValidator.addCapabilityValidator(capabilityRequest);
        if (var != null){
            System.out.println(var);
            return 0;
        }else{
            return capabilityDAO.addCapabilityToDatabase(connection, capabilityRequest);
        }
    }
}
