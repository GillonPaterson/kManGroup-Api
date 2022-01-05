package service;

import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.model.CapabilityLead;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.service.CapabiltyService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapabilityServiceTests {

    @Test
    void testServiceGetCapabilityLead() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        CapabilityLead lead1 = new CapabilityLead(1, "dave","boats","wow code is great","https://memegenerator.net/img/images/15109657/fat-warcraft-guy-from-south-park.jpg","Engineering");

        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
        Mockito.when(capabilityDAO.getCapabilityleadFromDataBase(connection, 1)).thenReturn(lead1);

        CapabiltyService capabiltyService = new CapabiltyService(capabilityDAO, connector);
        CapabilityLead returnedModel = capabiltyService.getCapabilitylead(1);

        Mockito.verify(connector).getConnection();
        Mockito.verify(capabilityDAO).getCapabilityleadFromDataBase(connection, 1);

        assertEquals(lead1, returnedModel);
    }

    @Test
    void testServiceGetAllCapabilityLeads() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        CapabilityLead lead1 = new CapabilityLead(1, "dave","boats","wow code is great","https://memegenerator.net/img/images/15109657/fat-warcraft-guy-from-south-park.jpg","Engineering");
        CapabilityLead lead2 = new CapabilityLead( 2, "Lee","Brown","i love Kainos ","https://cdn.vox-cdn.com/thumbor/BxA3f-dxx4UmGxOCNE_7P4V7fAs=/0x0:5157x3438/1200x800/filters:focal(880x1246:1704x2070)/cdn.vox-cdn.com/uploads/chorus_image/image/69106641/1201476988.0.jpg","Cyber Security");

        List<CapabilityLead> leadList = new ArrayList<>();
        leadList.add(lead1);
        leadList.add(lead2);

        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
        Mockito.when(capabilityDAO.getAllCapabilityleadsFromDataBase(connection)).thenReturn(leadList);

        CapabiltyService capabilityService = new CapabiltyService(capabilityDAO, connector);
        List<CapabilityLead> returnedList = capabilityService.getAllCapabilityLeads();

        Mockito.verify(connector).getConnection();
        Mockito.verify(capabilityDAO).getAllCapabilityleadsFromDataBase(connection);

        assertEquals(leadList, returnedList);
    }

    @Test
    public void TestServiceAddCapabilty() throws SQLException{

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
        CapabilityRequest capReq = new CapabilityRequest("test");
        CapabiltyService capServ = new CapabiltyService(capabilityDAO, connector);

        Mockito.when(capabilityDAO.addCapabilityToDatabase(connection, capReq)).thenReturn(20);
        int result = capServ.createCapability(capReq);

        Mockito.verify(capabilityDAO).addCapabilityToDatabase(connection, capReq);
        assertEquals(20,result);
    }
}
