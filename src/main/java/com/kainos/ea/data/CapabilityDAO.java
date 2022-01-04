package com.kainos.ea.data;

import com.kainos.ea.model.CapabilityLead;
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

    public List<CapabilityLead> getAllCapabilityleadsFromDataBase(Connection connection) throws SQLException{
        List<CapabilityLead> leadList = new ArrayList<>();
        String query = "Select leadID, leadFname, leadSname, leadPhoto, leadMessage, capabilities.jobCapability from capabilityLead inner join capabilities using(jobCapabilityID)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            CapabilityLead capLead = new CapabilityLead(rs.getInt("leadID"),rs.getString("leadFname"),rs.getString("leadSname"),rs.getString("leadPhoto"),rs.getString("leadMessage"), rs.getString("jobCapability"));
            leadList.add(capLead);
        }
        if(leadList.isEmpty()){
            throw new SQLException();
        }else{
            return leadList;
        }

    }
}
