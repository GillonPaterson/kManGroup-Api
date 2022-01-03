package com.kainos.ea.data;

import com.kainos.ea.model.JobFamilyModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobFamiliesDAO {
    public List<JobFamilyModel> getFamilies(Connection connection) throws SQLException{
        String query = "SELECT jobFamilyName, jobCapability FROM jobFamilies INNER JOIN capabilities using(jobCapabilityID)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        List<JobFamilyModel> jobFamilyModels = new ArrayList<>();

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            JobFamilyModel jobFamilyModel = new JobFamilyModel(rs.getString("jobCapability"), rs.getString("jobFamilyName"));
            jobFamilyModels.add(jobFamilyModel);
        }

        return jobFamilyModels;
    }
}
