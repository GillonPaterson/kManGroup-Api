package com.kainos.ea.data;

import com.kainos.ea.model.Competency;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecModel;
import com.kainos.ea.model.RoleMatrixModel;
import com.kainos.ea.model.JobTraining;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRolesDAO {
    public List<JobRole> getJobRolesFromDatabase(Connection connection) throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();
        String query = "SELECT jobRoleID, jobRole, jobFamilyName, jobCapability, jobBandLevel FROM jobRoles Inner join jobFamilies using(jobFamilyID) inner join capabilities using(jobCapabilityID) inner join bandLevels using (jobBandLevelID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobRole jobRole = new JobRole(rs.getInt("jobRoleID"), rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"), rs.getString("jobFamilyName"));
            jobRoles.add(jobRole);
        }
        return jobRoles;
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
        String query = "SELECT jobRoles.jobRole, jobRoles.jobRoleID, bandLevels.jobBandLevel, capabilities.jobCapability FROM jobRoles INNER JOIN bandLevels using (jobBandLevelID) inner join jobFamilies using(jobFamilyID) INNER JOIN capabilities using (jobCapabilityID)";

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
    public Competency getJobCompFromDatabase(Connection connection, int jobRoleID) throws SQLException {

        List<String> competencyStage = new ArrayList<>();

        String jobBandLevel = "";
        String query = "Select bandLevels.jobBandLevel, competenciesData.competencyStage from competenciesData inner join competencies using(competencyDataID) inner join bandLevels using(jobBandLevelID) inner join jobRoles using(jobBandLevelID) where jobRoleID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, jobRoleID);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            jobBandLevel = rs.getString("jobBandLevel");
            competencyStage.add(rs.getString("competencyStage"));
            System.out.println(competencyStage);
        }

        Competency competency = new Competency(jobBandLevel,competencyStage);

        if (competencyStage.isEmpty()){
            throw new SQLException();
        }else{
            return competency;
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
