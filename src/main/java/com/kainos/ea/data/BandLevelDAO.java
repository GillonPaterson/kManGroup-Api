package com.kainos.ea.data;

import com.kainos.ea.model.BandLevelModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BandLevelDAO {
    public List<String> getBandLevelFromDatabase(Connection connection) throws SQLException {
        List<String> bandLevels = new ArrayList<>();
        String query = "SELECT jobBandLevel FROM bandLevels ORDER BY importance";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            bandLevels.add(rs.getString("jobBandLevel"));
        }
        if(bandLevels.isEmpty()) {
            throw new SQLException();
        }else{
            return bandLevels;
        }
    }

    public List<BandLevelModel> getBandLevelAndImportanceFromDatabase(Connection connection) throws SQLException {
        List<BandLevelModel> bandLevels = new ArrayList<>();
        String query = "SELECT jobBandLevel, importance FROM bandLevels ORDER BY importance";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            BandLevelModel bandLevelModel = new BandLevelModel(rs.getString("jobBandLevel"), rs.getInt("importance"));
            bandLevels.add(bandLevelModel);
        }
        if(bandLevels.isEmpty()) {
            throw new SQLException();
        }else{
            return bandLevels;
        }
    }
}
