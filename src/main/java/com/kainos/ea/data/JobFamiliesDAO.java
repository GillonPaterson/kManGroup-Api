package com.kainos.ea.data;

import com.kainos.ea.model.JobFamilyModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobFamiliesDAO {
    public List<JobFamilyModel> getFamilies(Connection connection) throws SQLException {
        String query = "SELECT jobFamilyName, jobCapability FROM jobFamilies INNER JOIN capabilities using(jobCapabilityID)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        List<JobFamilyModel> jobFamilyModels = new ArrayList<>();

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String capability = rs.getString("jobCapability");
            String jobFamily = rs.getString("jobFamilyName");
            boolean added = false;
            for (int i = 0; i < jobFamilyModels.size(); i++) {
                if (jobFamilyModels.get(i).getJobCapability().equals(capability)) {
                    jobFamilyModels.get(i).getJobFamily().add(jobFamily);
                    added = true;
                }
            }
            if (!added) {
                List<String> jobFamilies = new ArrayList<>();
                jobFamilies.add(jobFamily);

                JobFamilyModel jobFamilyModel = new JobFamilyModel(capability, jobFamilies);
                jobFamilyModels.add(jobFamilyModel);
            }
        }

        return jobFamilyModels;
    }


    public List<String> getJobFamiliesFromDatabase(Connection connection) throws SQLException {
        List<String> families = new ArrayList<>();
        String query = "SELECT jobFamilyName FROM jobFamilies";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            families.add(rs.getString("jobFamilyName"));
        }
        if (families.isEmpty()) {
            throw new SQLException();
        } else {
            return families;
        }
    }
}
