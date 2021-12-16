package com.kainos.ea.data;

import com.kainos.ea.model.Competency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CapabilityDAO {
    public List<String> getJobCapabilitiesFromDatabase(Connection connection) throws SQLException {
        List<String> capabilities = new ArrayList<>();
        String query = "SELECT jobCapability FROM capabilities";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            capabilities.add(rs.getString("jobCapability"));
        }
        if(capabilities.isEmpty()) {
            throw new SQLException();
        }else{
            return capabilities;
        }
    }
}
