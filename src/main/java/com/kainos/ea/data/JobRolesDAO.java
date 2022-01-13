package com.kainos.ea.data;


import com.kainos.ea.model.AddJobRole;
import com.kainos.ea.model.EditJobRole;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecModel;
import com.kainos.ea.model.RoleMatrixModel;
import com.kainos.ea.model.JobTraining;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class JobRolesDAO {
    public List<JobRole> getJobRolesFromDatabase(Connection connection) throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();
        String query = "SELECT jobRoleID, jobRole, jobFamilyName, jobCapability, jobBandLevel FROM jobRoles Inner join jobFamilies using(jobFamilyID) inner join capabilities using(jobCapabilityID) inner join bandLevels using (jobBandLevelID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            JobRole jobRole = new JobRole(rs.getInt("jobRoleID"), rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"), rs.getString("jobFamilyName"));
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }


    public EditJobRole getJobRoleFromDatabase(Connection connection, int jobRoleID) throws SQLException {

        EditJobRole jobRole = new EditJobRole(0, "", "", "", "", "", "");
        String query = "SELECT jobRoleID, jobRole, jobSpec, jobBandLevel, jobFamilyName, jobLink, jobResponsibilities FROM jobRoles INNER JOIN bandLevels using(jobBandLevelID) INNER JOIN jobFamilies using(jobFamilyID) WHERE jobRoles.jobRoleID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, jobRoleID);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            jobRole = new EditJobRole(rs.getInt("jobRoleID"), rs.getString("jobRole"), rs.getString("jobSpec"), rs.getString("jobBandLevel"), rs.getString("jobFamilyName"), rs.getString("jobLink"), rs.getString("jobResponsibilities"));
            return jobRole;
        }
        return jobRole;
    }



    public Integer addJobRole(Connection connection, AddJobRole addJobRole) {
        int bandLevelID = 0;
        int jobFamilyID = 0;

        String query1 = "SELECT jobBandLevelID FROM bandLevels WHERE jobBandLevel = ?";
        try {
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, addJobRole.getJobBandLevel());

            ResultSet rs = statement1.executeQuery();
            while (rs.next()) {
                bandLevelID = rs.getInt("jobBandLevelID");
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }

        String query2 = "SELECT jobFamilyID FROM jobFamilies WHERE jobFamilyName = ?";
        try {
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, addJobRole.getJobFamily());

            ResultSet rs = statement2.executeQuery();
            while (rs.next()) {
                jobFamilyID = rs.getInt("jobFamilyID");
            }
        } catch (Exception e2) {
            System.out.println(e2);
        }


        String query3 = "Insert into jobRoles (jobRole, jobBandLevelID, jobSpec, jobLink, jobResponsibilities, jobFamilyID)" +"Values(?,?,?,?,?,?)";
        try {
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

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public Integer editJobRole(Connection connection, AddJobRole editJobRole, int jobRoleID) {
        int bandLevelID = 0;
        int jobFamilyID = 0;

        String query1 = "SELECT jobBandLevelID FROM bandLevels WHERE jobBandLevel = ?";
        try {
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, editJobRole.getJobBandLevel());

            ResultSet rs = statement1.executeQuery();
            while (rs.next()){
                bandLevelID = rs.getInt("jobBandLevelID");
            }
        } catch (Exception e1) {
            System.out.println(e1);
        }

        String query2 = "SELECT jobFamilyID FROM jobFamilies WHERE jobFamilyName = ?";
        try {
            PreparedStatement statement2 = connection.prepareStatement(query2);
            statement2.setString(1, editJobRole.getJobFamily());

            ResultSet rs = statement2.executeQuery();
            while (rs.next()) {
                jobFamilyID = rs.getInt("jobFamilyID");
            }
        } catch (Exception e2) {
            System.out.println(e2);
        }


        String query3 = "Update jobRoles set jobRole = ?, jobBandLevelID = ?, jobSpec = ?, jobLink = ?, jobResponsibilities = ?, jobFamilyID = ? where jobRoleID = ?";
        try {
            PreparedStatement statement3 = connection.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
            statement3.setString(1, editJobRole.getJobRole());
            statement3.setInt(2, bandLevelID);
            statement3.setString(3, editJobRole.getJobSpec());
            statement3.setString(4, editJobRole.getJobLink());
            statement3.setString(5, editJobRole.getJobResponsibilities());
            statement3.setInt(6, jobFamilyID);
            statement3.setInt(7, jobRoleID);

            statement3.executeUpdate();
            return 1;

        } catch (Exception e) {
            System.out.println("Failed to update the job role. " + e);
            return -1;
        }
    }


    public Integer deleteJobRole(Connection connection, int jobRoleID) {

        String query = "DELETE FROM jobRoles where jobRoleID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, jobRoleID);

            statement.executeUpdate();
            return 1;

        } catch (Exception e) {
            System.out.println("Failed to delete the job role. " + e);
            return -1;
        }
    }



    public JobSpecModel getJobSpecFromDatabase(Connection connection, int jobRoleID) throws SQLException {

        String query = "SELECT * FROM jobRoles WHERE jobRoleID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, jobRoleID);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            JobSpecModel jobSpecModel = new JobSpecModel(rs.getString("jobRole"), rs.getString("jobSpec"), rs.getString("jobLink"), rs.getString("jobResponsibilities"));
            return jobSpecModel;
        }
        throw new SQLException();
    }

    public List<RoleMatrixModel> getJobRoleMatrixFromDatabase(Connection connection) throws SQLException {

        List<RoleMatrixModel> roleMatrixModels = new ArrayList<>();
        String query = "SELECT jobRoles.jobRole, jobRoles.jobRoleID, bandLevels.jobBandLevel, capabilities.jobCapability FROM jobRoles INNER JOIN bandLevels using (jobBandLevelID) inner join jobFamilies using(jobFamilyID) INNER JOIN capabilities using (jobCapabilityID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            RoleMatrixModel roleMatrixModel = new RoleMatrixModel(rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"), rs.getString("jobRoleID"));
            roleMatrixModels.add(roleMatrixModel);
        }
        if (roleMatrixModels.isEmpty()) {
            throw new SQLException();
        } else {
            return roleMatrixModels;
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
