package com.kainos.ea.data;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.Competency;
import com.kainos.ea.model.CompetencyData;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.CapabilityValidator;

import java.sql.*;
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

        Competency competency = new Competency(jobBandLevel,competencyStage);

        if (competencyStage.isEmpty()){
            throw new SQLException();
        }else{
            return competency;
        }
    }

    public List<CompetencyData>getAllCompDataFromDatabase(Connection connection) throws SQLException {

        List<CompetencyData> competencyDataList = new ArrayList<>();

        String query = "Select * from competenciesData";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            CompetencyData competencyData = new CompetencyData(rs.getInt("competencyDataID"),rs.getString("competencyStage"));
            competencyDataList.add(competencyData);
        }
        return competencyDataList;
    }

    public boolean insertIntoCompetencies(Connection connection, int bandLevelID, int competencyDataID) throws SQLException{
        String query = "INSERT INTO competencies (jobBandLevelID, competencyDataID) VALUES (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bandLevelID);
        preparedStatement.setInt(2, competencyDataID);

        int count = preparedStatement.executeUpdate();
        if(count > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
