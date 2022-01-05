package com.kainos.ea.data;

import com.kainos.ea.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRolesDAO {
    public List<JobRole> getJobRolesFromDatabase(Connection connection) throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();
        String query = "SELECT jobRoleID, jobRole, jobCapability, jobBandLevel FROM jobRoles Inner join capabilities using(jobCapabilityID) inner join bandLevels using (jobBandLevelID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobRole jobRole = new JobRole(rs.getInt("jobRoleID"), rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"));
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }


    public Integer addJobRole(Connection connection, AddJobRole addJobRole){
        int bandLevelID = 0;
        int jobFamilyID = 0;

        String query1 = "SELECT jobBandLevelID FROM bandLevels WHERE jobBandLevel = ?";
        try {
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, addJobRole.getJobBandLevel());

            ResultSet rs = statement1.executeQuery();
            while (rs.next()){
                bandLevelID = rs.getInt("jobBandLevelID");
            }
        }catch (Exception e1)
        {
            System.out.println(e1);
        }

        String query2 = "SELECT jobFamilyID FROM jobFamilies WHERE jobFamilyName = ?";
        try {
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, addJobRole.getJobFamily());

            ResultSet rs = statement2.executeQuery();
            while (rs.next()){
                jobFamilyID = rs.getInt("jobFamilyID");
            }
        }catch (Exception e2)
        {
            System.out.println(e2);
        }


        String query3 = "Insert into jobRoles (jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities, jobFamilyID)" +"Values(?,?,?,?,?,?)";
        try{
            PreparedStatement statement3 = connection.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
            statement3.setString(1, addJobRole.getJobRole());
            statement3.setInt(2, bandLevelID);
            statement3.setString(3, addJobRole.getJobSpec());
            statement3.setString(4, addJobRole.getJobLink());
            statement3.setString(5, addJobRole.getJobResponsibilities());
            statement3.setInt(6, jobFamilyID);

            statement3.execute();
            try(ResultSet generatedKeys = statement3.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    throw new SQLException("create job role failed");
                }
                return generatedKeys.getInt(1);
            }

        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public JobSpecModel getJobSpecFromDatabase(Connection connection, int jobRoleID) throws SQLException {

        String query = "SELECT * FROM jobRoles WHERE jobRoleID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, jobRoleID);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobSpecModel jobSpecModel = new JobSpecModel(rs.getString("jobRole"), rs.getString("jobSpec"), rs.getString("jobLink"), rs.getString("jobResponsibilities"));
            return jobSpecModel;
        }
        throw new SQLException();
    }

    public List<RoleMatrixModel> getJobRoleMatrixFromDatabase(Connection connection) throws SQLException {

        List<RoleMatrixModel> roleMatrixModels = new ArrayList<>();
        String query = "SELECT jobRoles.jobRole, jobRoles.jobRoleID, bandLevels.jobBandLevel, capabilities.jobCapability FROM jobRoles INNER JOIN bandLevels using (jobBandLevelID) INNER JOIN capabilities using (jobCapabilityID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            RoleMatrixModel roleMatrixModel = new RoleMatrixModel(rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"), rs.getString("jobRoleID"));
            roleMatrixModels.add(roleMatrixModel);
        }
        if (roleMatrixModels.isEmpty()){
            throw new SQLException();
        }else{
            return roleMatrixModels;
        }
    }
    public List<Competency> getJobCompFromDatabase(Connection connection, int jobRoleID) throws SQLException {

        List<Competency> complist = new ArrayList<>();
        String query = "Select bandLevels.jobBandLevel, competenciesData.competencyStage from competenciesData inner join competencies using(competencyDataID) inner join bandLevels using(jobBandLevelID) inner join jobRoles using(jobBandLevelID) where jobroleID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, jobRoleID);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Competency competency = new Competency(rs.getString("jobBandLevel"),rs.getString("competencyStage"));
            complist.add(competency);
        }

        if (complist.isEmpty()){
            throw new SQLException();
        }else{
            return complist;
        }
    }


    public List<JobTraining> getJobTrainingFromDatabase(Connection connection, String bandLevel) throws SQLException {
        List<JobTraining> training = new ArrayList<>();
        String query = "SELECT bandLevels.jobBandLevel, training.trainingName, training.trainingLink, training.trainingGroup FROM bandLevels inner join bandLevelsTraining using(jobBandLevelID) inner join training using(trainingID) WHERE (bandLevels.jobBandLevel = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bandLevel);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobTraining jobTraining = new JobTraining(rs.getString("jobBandLevel"), rs.getString("trainingName"), rs.getString("trainingLink"), rs.getString("trainingGroup"));
            training.add(jobTraining);
        }
        return training;
    }

}
