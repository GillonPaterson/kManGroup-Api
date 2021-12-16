package com.kainos.ea.service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.Competency;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecModel;
import com.kainos.ea.model.RoleMatrixModel;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JobRolesService {

    JobRolesDAO jobRolesDAO = new JobRolesDAO();
    BandLevelDAO bandLevelDAO = new BandLevelDAO();
    CapabilityDAO capabilityDAO = new CapabilityDAO();
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

    public JobSpecModel getJobSpec(int jobRoleID) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobSpecFromDatabase(connection, jobRoleID);
    }

    public Competency getComp(String bandLevel) throws SQLException {
        Connection connection = databaseConnector.getConnection();
        return jobRolesDAO.getJobCompFromDatabase(connection, bandLevel);
    }

    public String[][] getRoleMatrix() throws SQLException{
        Connection connection = databaseConnector.getConnection();

        List<String> bandLevel = bandLevelDAO.getBandLevelFromDatabase(connection);
        List<String> capability = capabilityDAO.getJobCapabilitiesFromDatabase(connection);

        List<RoleMatrixModel> roleMatrixModels = jobRolesDAO.getJobRoleMatrixFromDatabase(connection);

        String[][] roleMatrix = new String[bandLevel.size()][capability.size()+1];

        for (int i =0; i < bandLevel.size(); i++){
            roleMatrix[i][0] = bandLevel.get(i);
        }
        roleMatrix[0][0] = "Job Band Level";
        for (int i =1; i < capability.size()+1; i++){
            roleMatrix[0][i] = capability.get(i-1);
        }
        for (int row = 1; row < roleMatrix.length; row++){
            for (int col = 1; col < roleMatrix[0].length; col++){
                for (int i = 0; i < roleMatrixModels.size(); i++){
                    if (roleMatrixModels.get(i).bandLevel.equals(roleMatrix[row][0]) && roleMatrixModels.get(i).capability.equals(roleMatrix[0][col])){
                        if(roleMatrix[row][col] == null){
                            roleMatrix[row][col] = roleMatrixModels.get(i).jobRole;
                        }else{
                            roleMatrix[row][col] += (", " +roleMatrixModels.get(i).jobRole);
                        }
                    }
                }
                if (roleMatrix[row][col] == null){
                    roleMatrix[row][col] = "";
                }
            }
        }

        return roleMatrix;
    }
}
