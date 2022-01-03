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
        String query = "SELECT jobRoleID, jobRole, jobCapability, jobBandLevel FROM jobRoles Inner join capabilities using(jobCapabilityID) inner join bandLevels using (jobBandLevelID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobRole jobRole = new JobRole(rs.getInt("jobRoleID"), rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"));
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
        String query = "SELECT jobRoles.jobRole, bandLevels.jobBandLevel, capabilities.jobCapability FROM jobRoles INNER JOIN bandLevels using (jobBandLevelID) INNER JOIN capabilities using (jobCapabilityID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            RoleMatrixModel roleMatrixModel = new RoleMatrixModel(rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"));
            roleMatrixModels.add(roleMatrixModel);
        }
        if (roleMatrixModels.isEmpty()){
            throw new SQLException();
        }else{
            return roleMatrixModels;
        }
    }
    public Competency getJobCompFromDatabase(Connection connection, int jobRoleID) throws SQLException {

        String query = "Select bandLevels.jobBandLevel, competenciesData.competencyStage1, competenciesData.competencyStage2,competenciesData.competencyStage3,competenciesData.competencyStage4,jobRoles.competencyStage from competenciesData inner join competencies using(competencyDataID) inner join bandLevels using(jobBandLevelID) inner join jobRoles using(jobBandLevelID) where jobroleID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, jobRoleID);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Competency competency = new Competency(rs.getString("jobBandLevel"), rs.getString("competencyStage1"),rs.getString("competencyStage2"),rs.getString("competencyStage3"),rs.getString("competencyStage4"),rs.getString("competencyStage"));
            return competency;
        }
        throw new SQLException();
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

    /*
    public List<JobTraining> getJobTrainingDPFromDatabase(Connection connection, String bandLevel) throws SQLException {
        List<JobTraining> training = new ArrayList<>();
        String query = "SELECT bandLevels.jobBandLevel, training.trainingName, training.trainingLink, training.trainingGroup FROM bandLevels inner join bandLevelsTraining using(jobBandLevelID) inner join training using(trainingID) WHERE (training.trainingGroup = 'Development programmes' AND bandLevels.jobBandLevel = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bandLevel);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobTraining jobTraining = new JobTraining(rs.getString("jobBandLevel"), rs.getString("trainingName"), rs.getString("trainingLink"), rs.getString("trainingGroup"));
            training.add(jobTraining);
        }
        return training;
    }


    public List<JobTraining> getJobTrainingPSFromDatabase(Connection connection, String bandLevel) throws SQLException {
        List<JobTraining> training = new ArrayList<>();
        String query = "SELECT bandLevels.jobBandLevel, training.trainingName, training.trainingLink, training.trainingGroup FROM bandLevels inner join bandLevelsTraining using(jobBandLevelID) inner join training using(trainingID) WHERE (training.trainingGroup = 'Professional skills' AND bandLevels.jobBandLevel = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bandLevel);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobTraining jobTraining = new JobTraining(rs.getString("jobBandLevel"), rs.getString("trainingName"), rs.getString("trainingLink"), rs.getString("trainingGroup"));
            training.add(jobTraining);
        }
        return training;
    }


    public List<JobTraining> getJobTrainingTSFromDatabase(Connection connection, String bandLevel) throws SQLException {
        List<JobTraining> training = new ArrayList<>();
        String query = "SELECT bandLevels.jobBandLevel, training.trainingName, training.trainingLink, training.trainingGroup FROM bandLevels inner join bandLevelsTraining using(jobBandLevelID) inner join training using(trainingID) WHERE (training.trainingGroup = 'Technical skills' AND bandLevels.jobBandLevel = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bandLevel);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobTraining jobTraining = new JobTraining(rs.getString("jobBandLevel"), rs.getString("trainingName"), rs.getString("trainingLink"), rs.getString("trainingGroup"));
            training.add(jobTraining);
        }
        return training;
    }
     */

}
