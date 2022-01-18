package com.kainos.ea.data;

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
        if (bandLevels.isEmpty()) {
            throw new SQLException();
        } else {
            return bandLevels;
        }
    }
}
