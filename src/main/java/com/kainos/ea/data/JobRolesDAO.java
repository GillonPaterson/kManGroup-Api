package com.kainos.ea.data;

import com.kainos.ea.model.Competency;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecModel;
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

    public Competency getJobCompFromDatabase(Connection connection, String bandLevel) throws SQLException {

        String query = "SELECT competencyName,competencyData FROM competencies WHERE competencyName = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bandLevel);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Competency competency = new Competency(rs.getString("competencyName"), rs.getString("competencyData"));
            return competency;
        }
        throw new SQLException();
    }

    public List<JobTraining> getJobTrainingFromDatabase(Connection connection, String bandLevel) throws SQLException {
        List<JobTraining> training = new ArrayList<>();
        String query = "SELECT bandLevels.jobBandLevel, training.trainingLink FROM bandLevels inner join bandLevelsTraining using(jobBandLevelID) inner join training using(trainingID) WHERE jobBandLevel = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bandLevel);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobTraining jobTraining = new JobTraining(rs.getString("jobBandLevel"), rs.getString("trainingLink"));
            training.add(jobTraining);
        }
        return training;
    }


}
