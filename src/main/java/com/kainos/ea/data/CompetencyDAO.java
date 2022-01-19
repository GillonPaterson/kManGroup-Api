package com.kainos.ea.data;

import com.kainos.ea.model.Competency;
import com.kainos.ea.model.CompetencyData;

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

    public List<CompetencyData> getAllCompDataFromDatabase(Connection connection) throws SQLException {

        List<CompetencyData> competencyDataList = new ArrayList<>();

        String query = "Select * from competenciesData";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            CompetencyData competencyData = new CompetencyData(rs.getInt("competencyDataID"), rs.getString("competencyStage"));
            competencyDataList.add(competencyData);
        }
        return competencyDataList;
    }

    public boolean insertIntoCompetencies(Connection connection, int bandLevelID, int competencyDataID) throws SQLException {
        String query = "INSERT INTO competencies (jobBandLevelID, competencyDataID) VALUES (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bandLevelID);
        preparedStatement.setInt(2, competencyDataID);

        int count = preparedStatement.executeUpdate();
        return count > 0;
    }

    public boolean checkCompetencyID(Connection connection, int[] competencyIDs) throws SQLException {
        String query = "SELECT COUNT(competencyDataID) from competenciesData where competencyDataID in (?";
        for (int i = 1; i < competencyIDs.length; i++) {
            query = query + ",?";
        }
        query = query + ")";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < competencyIDs.length; i++) {
            preparedStatement.setInt(i + 1, competencyIDs[i]);
        }

        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        return rs.getInt(1) == competencyIDs.length;
    }
}
