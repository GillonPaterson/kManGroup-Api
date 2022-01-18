package com.kainos.ea.service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobRolesDAO;

import com.kainos.ea.model.AddJobRole;
import com.kainos.ea.model.EditJobRole;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecModel;
import com.kainos.ea.model.RoleMatrixResponseModel;
import com.kainos.ea.model.RoleMatrixModel;

import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobRoleValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobRolesService {

    JobRolesDAO jobRolesDAO = new JobRolesDAO();
    BandLevelDAO bandLevelDAO = new BandLevelDAO();
    CapabilityDAO capabilityDAO = new CapabilityDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();
    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    public JobRolesService() {

    }

    public JobRolesService(JobRolesDAO jobRolesDAO, DatabaseConnector databaseConnector) {
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
    }

    public JobRolesService(JobRolesDAO jobRolesDAO, DatabaseConnector databaseConnector, JobRoleValidator jobRoleValidator) {
        this.databaseConnector = databaseConnector;
        this.jobRolesDAO = jobRolesDAO;
        this.jobRoleValidator = jobRoleValidator;
    }

    public JobRolesService(JobRolesDAO jobRolesDAO, BandLevelDAO bandLevelDAO, CapabilityDAO capabilityDAO, DatabaseConnector databaseConnector) {
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
        this.bandLevelDAO = bandLevelDAO;
        this.capabilityDAO = capabilityDAO;
    }

    public List<JobRole> getJobRoles() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobRolesFromDatabase(connection);
    }


    public List<JobRole> getJobRolesFilter(List<String> capabilityFilters, List<String> bandLevelFilters, List<String> familyFilters, String nameFilter) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobRolesFromDatabaseWithFilter(connection, capabilityFilters, bandLevelFilters, familyFilters, nameFilter);
    }


    public EditJobRole getJobRole(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobRoleFromDatabase(connection, jobRoleID);
    }

    public Integer deleteJobRole(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.deleteJobRole(connection, jobRoleID);
    }


    public Integer addJobRole(AddJobRole addJobRoles) {
        Connection connection = databaseConnector.getConnection();
        String var = jobRoleValidator.addJobRoleValidator(addJobRoles);

        if (var != null) {
            System.out.println(var);
            return 0;
        } else {
            return jobRolesDAO.addJobRole(connection, addJobRoles);
        }
    }

    public Integer editJobRole(AddJobRole editJobRoles, int jobRoleID) {
        Connection connection = databaseConnector.getConnection();
        String var = jobRoleValidator.addJobRoleValidator(editJobRoles);

        if (var != null) {
            System.out.println(var);
            return 0;
        } else {
            return jobRolesDAO.editJobRole(connection, editJobRoles, jobRoleID);
        }
    }

    public JobSpecModel getJobSpec(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobSpecFromDatabase(connection, jobRoleID);
    }

    public RoleMatrixResponseModel getRoleMatrix() throws SQLException {
        Connection connection = databaseConnector.getConnection();

        List<String> bandLevel = bandLevelDAO.getBandLevelFromDatabase(connection);
        List<String> capability = capabilityDAO.getJobCapabilitiesFromDatabase(connection);

        List<RoleMatrixModel> roleMatrixModels = jobRolesDAO.getJobRoleMatrixFromDatabase(connection);

        RoleMatrixResponseModel roleMatrixResponseModel = new RoleMatrixResponseModel(roleMatrixModels, bandLevel, capability);

        return roleMatrixResponseModel;
    }

}
