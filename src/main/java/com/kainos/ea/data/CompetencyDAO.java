package com.kainos.ea.data;

import com.kainos.ea.model.Competency;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompetencyDAO {

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

        Competency competency = new Competency(jobBandLevel, competencyStage);

        if (competencyStage.isEmpty()) {
            throw new SQLException();
        } else {
            return competency;
        }
    }
}
