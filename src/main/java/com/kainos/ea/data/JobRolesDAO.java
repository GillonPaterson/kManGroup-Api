package com.kainos.ea.data;

import com.kainos.ea.model.JobRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRolesDAO {
    public List<JobRole> getJobRolesFromDatabase(Connection connection) throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();
        String query = "SELECT jobRoleID, jobRole, jobCapability, jobBandLevel FROM jobRoles";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobRole jobRole = new JobRole(rs.getInt("jobRoleID"), rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"));
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }

    public List<JobRole> getJobSpecFromDatabase(Connection connection, int jobRoleID) throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();
        String query = "SELECT * FROM jobRoles WHERE jobRoleID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, jobRoleID);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobRole jobRole = new JobRole(rs.getInt("jobRoleID"), rs.getString("jobRole"), rs.getString("jobCapability"), rs.getString("jobBandLevel"), rs.getString("jobSpec"));
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }

    public List<JobRole> getJobCompFromDatabase(Connection connection, String bandLevel) throws SQLException {
        List<JobRole> jobRoles = new ArrayList<>();
        String query = "SELECT competencyName,competencyData FROM competencies WHERE competencyName = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bandLevel);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobRole jobRole = new JobRole(rs.getString("competencyName"), rs.getString("competencyData"));
            jobRoles.add(jobRole);
        }
        return jobRoles;
    }


}
