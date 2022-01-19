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

        String query = "SELECT jobRoleID, jobRole, jobFamilyName, jobCapability, jobBandLevel FROM jobRoles Inner join jobFamilies using(jobFamilyID) inner join capabilities using(jobCapabilityID) inner join bandLevels using (jobBandLevelID) where";

        if (!capabilityFilters.isEmpty()) {
            if (capabilityFilters.stream().count() > 1) {
                for (int i = 0; i < capabilityFilters.stream().count(); i++) {
                    if (capabilityFilters.stream().count() == i + 1) {
                        query = query + " jobCapability = ?)";
                    } else if (i == 0) {
                        query = query + " (jobCapability = ? or";
                    } else {
                        query = query + " jobCapability = ? or";
                    }
                }
            } else {
                query = query + " (jobCapability = ?)";
            }
        }

        if (!bandLevelFilters.isEmpty()) {
            if (bandLevelFilters.stream().count() > 1) {
                for (int i = 0; i < bandLevelFilters.stream().count(); i++) {
                    if (bandLevelFilters.stream().count() == i + 1) {
                        query = query + " jobBandLevel = ?)";
                    } else if (i == 0 && !capabilityFilters.isEmpty()) {
                        query = query + " and (jobBandLevel = ? or";
                    } else if (i == 0 && capabilityFilters.isEmpty()) {
                        query = query + " (jobBandLevel = ? or";
                    } else {
                        query = query + " jobBandLevel = ? or";
                    }
                }
            } else {
                if (!capabilityFilters.isEmpty()) {
                    query = query + " and (jobBandLevel = ?)";
                } else {
                    query = query + " (jobBandLevel = ?)";
                }
            }
        }

        if (!familyFilters.isEmpty()) {
            if (familyFilters.stream().count() > 1) {
                for (int i = 0; i < familyFilters.stream().count(); i++) {
                    if (familyFilters.stream().count() == i + 1) {
                        query = query + " jobFamilyName = ?)";
                    } else if (i == 0 && (!capabilityFilters.isEmpty() || !bandLevelFilters.isEmpty())) {
                        query = query + " and (jobFamilyName = ? or";
                    } else if (i == 0 && capabilityFilters.isEmpty() && bandLevelFilters.isEmpty()) {
                        query = query + " (jobFamilyName = ? or";
                    } else {
                        query = query + " jobFamilyName = ? or";
                    }
                }
            } else {
                if (!capabilityFilters.isEmpty() || !bandLevelFilters.isEmpty()) {
                    query = query + " and (jobFamilyName = ?)";
                } else {
                    query = query + " (jobFamilyName = ?)";
                }
            }
        }

        if (nameFilter != null) {
            if (!capabilityFilters.isEmpty() || !bandLevelFilters.isEmpty() || !familyFilters.isEmpty()) {
                query = query + " and (jobRole LIKE ?)";
            } else {
                query = query + " (jobRole LIKE ?)";
            }
        }

        return jobRolesDAO.getJobRolesFromDatabaseWithFilter(connection, capabilityFilters, bandLevelFilters, familyFilters, nameFilter, query);
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
