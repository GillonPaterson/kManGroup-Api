package com.kainos.ea.service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.*;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobRoleValidator;
import org.checkerframework.checker.units.qual.C;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class JobRolesService {

    JobRolesDAO jobRolesDAO = new JobRolesDAO();
    BandLevelDAO bandLevelDAO = new BandLevelDAO();
    CapabilityDAO capabilityDAO = new CapabilityDAO();
    DatabaseConnector databaseConnector = new DatabaseConnector();
    JobRoleValidator jobRoleValidator = new JobRoleValidator();

    public JobRolesService(){

    }

    public JobRolesService(JobRolesDAO jobRolesDAO, DatabaseConnector databaseConnector){
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
    }

    public JobRolesService(CapabilityDAO capabilityDAO, DatabaseConnector databaseConnector){
        this.databaseConnector = databaseConnector;
        this.capabilityDAO = capabilityDAO;
    }

    public JobRolesService(JobRolesDAO jobRolesDAO, BandLevelDAO bandLevelDAO, CapabilityDAO capabilityDAO, DatabaseConnector databaseConnector){
        this.jobRolesDAO = jobRolesDAO;
        this.databaseConnector = databaseConnector;
        this.bandLevelDAO = bandLevelDAO;
        this.capabilityDAO = capabilityDAO;
    }

    public List<JobRole> getJobRoles() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobRolesFromDatabase(connection);
    }


    public Integer addJobRole(AddJobRole addJobRoles) {
        Connection connection = databaseConnector.getConnection();
        String var = jobRoleValidator.addJobRoleValidator(addJobRoles);

        if (var != null){
            System.out.println(var);
            return 0;
        }else{
            return jobRolesDAO.addJobRole(connection, addJobRoles);
        }
    }



    public JobSpecModel getJobSpec(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobSpecFromDatabase(connection, jobRoleID);
    }

    public Competency getComp(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobCompFromDatabase(connection,jobRoleID);
    }

    public List<String> getJobBandLevels() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return bandLevelDAO.getBandLevelFromDatabase(connection);
    }

    public List<String> getJobCapabilities() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return capabilityDAO.getJobCapabilitiesFromDatabase(connection);
    }

    public List<JobTraining> getJobTraining(String bandLevel) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobTrainingFromDatabase(connection, bandLevel);
    }








    public RoleMatrixResponseModel getRoleMatrix() throws SQLException{
        Connection connection = databaseConnector.getConnection();

        List<String> bandLevel = bandLevelDAO.getBandLevelFromDatabase(connection);
        List<String> capability = capabilityDAO.getJobCapabilitiesFromDatabase(connection);

        List<RoleMatrixModel> roleMatrixModels = jobRolesDAO.getJobRoleMatrixFromDatabase(connection);

        RoleMatrixResponseModel roleMatrixResponseModel = new RoleMatrixResponseModel(roleMatrixModels, bandLevel, capability);

        return roleMatrixResponseModel;


//        String[][] roleMatrix = new String[bandLevel.size()+1][capability.size()+1];
//
//        for (int i = 1; i < bandLevel.size() + 1; i++){
//            roleMatrix[i][0] = bandLevel.get(i-1);
//        }
//        roleMatrix[0][0] = "Job Band Level";
//        for (int i =1; i < capability.size()+1; i++){
//            roleMatrix[0][i] = capability.get(i-1);
//        }
//        for (int row = 1; row < roleMatrix.length; row++){
//            for (int col = 1; col < roleMatrix[0].length; col++){
//                for (int i = 0; i < roleMatrixModels.size(); i++){
//                    if (roleMatrixModels.get(i).bandLevel.equals(roleMatrix[row][0]) && roleMatrixModels.get(i).capability.equals(roleMatrix[0][col])){
//                        if(roleMatrix[row][col] == null){
//                            roleMatrix[row][col] = roleMatrixModels.get(i).jobRole + ";" + roleMatrixModels.get(i).jobRoleID;
//                        }else{
//                            roleMatrix[row][col] += (", " +roleMatrixModels.get(i).jobRole + ";" + roleMatrixModels.get(i).jobRoleID);
//                        }
//                    }
//                }
//                if (roleMatrix[row][col] == null){
//                    roleMatrix[row][col] = "";
//                }
//            }
//        }
    }

}
