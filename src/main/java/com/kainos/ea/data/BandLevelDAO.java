package com.kainos.ea.data;

import com.kainos.ea.model.BandLevelModel;

import java.sql.*;
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
    public boolean updateImportance(Connection connection, int importance) throws SQLException{
        String query = "UPDATE bandLevels set importance = ? where importance = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, importance + 1);
        preparedStatement.setInt(2, importance);
        int count = preparedStatement.executeUpdate();
        if(count > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public int insertBandLevelData(Connection connection, BandLevelModel bandLevelInfo) throws SQLException{
        String query = "INSERT INTO bandLevels (jobBandLevel, importance) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, bandLevelInfo.getJobBandLevel());
        preparedStatement.setInt(2, bandLevelInfo.getImportance());
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        while (rs.next()){
            return rs.getInt(1);
        }
        throw new SQLException("Create Band Level Failed");
    }

    public int getMaxImportance(Connection connection) throws SQLException {
        String query = "SELECT importance FROM bandLevels ORDER BY importance DESC LIMIT 1";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            return rs.getInt(1);
        }
        throw new SQLException("SQL While getting max importance");
    }
}
