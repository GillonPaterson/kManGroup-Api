package com.kainos.ea.data;

import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.CapabilityLead;
import com.kainos.ea.model.CapabilityRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        if (capabilities.isEmpty()) {
            throw new SQLException();
        } else {
            return capabilities;
        }
    }

    public List<CapabilityLead> getAllCapabilityleadsFromDataBase(Connection connection) throws SQLException {
        List<CapabilityLead> leadList = new ArrayList<>();
        String query = "Select leadID, leadFname, leadSname, leadPhoto, leadMessage, capabilities.jobCapability from capabilityLead inner join capabilities using(jobCapabilityID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            CapabilityLead capLead = new CapabilityLead(rs.getInt("leadID"), rs.getString("leadFname"), rs.getString("leadSname"), rs.getString("leadPhoto"), rs.getString("leadMessage"), rs.getString("jobCapability"));
            leadList.add(capLead);
        }
        if (leadList.isEmpty()) {
            throw new SQLException();
        } else {
            return leadList;
        }

    }

    public CapabilityLead getCapabilityleadFromDataBase(Connection connection, int leadID) throws SQLException {
        String query = "Select leadID, leadFname, leadSname, leadPhoto, leadMessage, capabilities.jobCapability from capabilityLead inner join capabilities using(jobCapabilityID) where leadID = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, leadID);


        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            CapabilityLead capLead = new CapabilityLead(rs.getInt("leadID"), rs.getString("leadFname"), rs.getString("leadSname"), rs.getString("leadMessage"), rs.getString("leadPhoto"), rs.getString("jobCapability"));
            return capLead;
        }

        throw new SQLException();
    }

    public Integer addCapabilityToDatabase(Connection connection, CapabilityRequest capabilityRequest) throws SQLException {
        String query = "Insert into capabilities(jobCapability) values(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, capabilityRequest.getCapabilityName());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (!generatedKeys.next()) {
                    throw new SQLException("create user failed");
                }
                return generatedKeys.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }


    public List<Capabilities> getAllCapabilitiesFromDataBase(Connection connection) throws SQLException {
        String query = "Select jobCapabilityID, jobCapability from capabilities";
        List<Capabilities> capList = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Capabilities cap = new Capabilities(rs.getInt("jobCapabilityID"), rs.getString("jobCapability"));
            capList.add(cap);
        }

        if (capList.isEmpty()) {
            throw new SQLException();
        } else {
            return capList;
        }
    }


    public boolean updateCapability(Connection connection, Capabilities capabilities) throws SQLException {
        System.out.println("updating");
        String query = "Update capabilities Set jobCapability = ? Where jobCapabilityID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, capabilities.getCapabilityName());
            preparedStatement.setInt(2, capabilities.getCapabilityID());

            int count = preparedStatement.executeUpdate();
            return count > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SQLException("update user failed");

        }

    }
}
