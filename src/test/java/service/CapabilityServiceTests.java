package service;

import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.CapabilityLead;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.service.CapabiltyService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.CapabilityValidator;
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

        CapabilityLead lead1 = new CapabilityLead(1, "dave", "boats", "wow code is great", "https://memegenerator.net/img/images/15109657/fat-warcraft-guy-from-south-park.jpg", "Engineering");

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

        CapabilityLead lead1 = new CapabilityLead(1, "dave", "boats", "wow code is great", "https://memegenerator.net/img/images/15109657/fat-warcraft-guy-from-south-park.jpg", "Engineering");
        CapabilityLead lead2 = new CapabilityLead(2, "Lee", "Brown", "i love Kainos ", "https://cdn.vox-cdn.com/thumbor/BxA3f-dxx4UmGxOCNE_7P4V7fAs=/0x0:5157x3438/1200x800/filters:focal(880x1246:1704x2070)/cdn.vox-cdn.com/uploads/chorus_image/image/69106641/1201476988.0.jpg", "Cyber Security");

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
    public void testServiceAddCapabilty() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);
        CapabilityValidator capabilityValidator = Mockito.mock(CapabilityValidator.class);

        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
        CapabilityRequest capReq = new CapabilityRequest("Engineering");
        CapabiltyService capServ = new CapabiltyService(capabilityDAO, connector, capabilityValidator);

        Mockito.when(capabilityValidator.addCapabilityValidator(capReq)).thenReturn(null);
        Mockito.when(capabilityDAO.addCapabilityToDatabase(connection, capReq)).thenReturn(20);
        int result = capServ.createCapability(capReq);

        Mockito.verify(capabilityDAO).addCapabilityToDatabase(connection, capReq);
        assertEquals(20, result);
    }

    @Test
    public void testServiceAddCapabiltyValidatorRetrunsErrorForNumbersInName() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);

        CapabilityValidator capabilityValidator = Mockito.mock(CapabilityValidator.class);
        CapabilityRequest capReq = new CapabilityRequest("12345");
        CapabiltyService capServ = new CapabiltyService(capabilityValidator);

        Mockito.when(capabilityValidator.addCapabilityValidator(capReq)).thenReturn("capability name cannot contain numbers");
        int result = capServ.createCapability(capReq);

        Mockito.verify(capabilityValidator).addCapabilityValidator(capReq);
        assertEquals(0, result);
    }

    @Test
    public void testServiceAddCapabiltyValidatorRetrunsErrorForTooManyCharacters() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);

        CapabilityValidator capabilityValidator = Mockito.mock(CapabilityValidator.class);
        CapabilityRequest capReq = new CapabilityRequest("fdnvjkfvbndfjkbngdfbvnvlkadsnfvlsdkvnsdvklsvndlsvsdklvnlskvnsdkvnsdnvklsvnksdnvkldsnvsdkvnsdlnvsd");
        CapabiltyService capServ = new CapabiltyService(capabilityValidator);

        Mockito.when(capabilityValidator.addCapabilityValidator(capReq)).thenReturn("capability name cannot be anymore than 20 characters");
        int result = capServ.createCapability(capReq);

        Mockito.verify(capabilityValidator).addCapabilityValidator(capReq);
        assertEquals(0, result);
    }

    @Test
    public void testServiceAddCapabiltyValidatorRetrunsErrorForSpaces() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);

        CapabilityValidator capabilityValidator = Mockito.mock(CapabilityValidator.class);
        CapabilityRequest capReq = new CapabilityRequest(" letters");
        CapabiltyService capServ = new CapabiltyService(capabilityValidator);

        Mockito.when(capabilityValidator.addCapabilityValidator(capReq)).thenReturn("capability name cannot contain empty spaces");
        int result = capServ.createCapability(capReq);

        Mockito.verify(capabilityValidator).addCapabilityValidator(capReq);
        assertEquals(0, result);
    }

    @Test
    void testServiceGetAllCapabilities() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        Capabilities cap1 = new Capabilities(1, "Engineering");
        Capabilities cap2 = new Capabilities(2, "Cyber Security");

        List<Capabilities> caplist = new ArrayList<>();
        caplist.add(cap1);
        caplist.add(cap2);

        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
        Mockito.when(capabilityDAO.getAllCapabilitiesFromDataBase(connection)).thenReturn(caplist);

        CapabiltyService capabilityService = new CapabiltyService(capabilityDAO, connector);
        List<Capabilities> returnedList = capabilityService.getAllCapabilites();

        Mockito.verify(connector).getConnection();
        Mockito.verify(capabilityDAO).getAllCapabilitiesFromDataBase(connection);

        assertEquals(caplist, returnedList);
    }

    @Test
    public void testServiceUpdateCapabiltyValidatorRetrunsErrorForNumbersInName() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);

        CapabilityValidator capabilityValidator = Mockito.mock(CapabilityValidator.class);
        Capabilities capReq = new Capabilities(1, "Engineering");
        CapabiltyService capServ = new CapabiltyService(capabilityValidator);

        Mockito.when(capabilityValidator.updateCapabilityValidator(capReq)).thenReturn("capability name cannot contain numbers");
        boolean result = capServ.updateCapability(capReq);

        Mockito.verify(capabilityValidator).updateCapabilityValidator(capReq);
        assertEquals(false, result);
    }

    @Test
    public void testServiceUpdateCapabiltyValidatorRetrunsErrorForTooManyCharacters() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);

        CapabilityValidator capabilityValidator = Mockito.mock(CapabilityValidator.class);
        Capabilities capReq = new Capabilities(1, "fdnvjkfvbndfjkbngdfbvnvlkadsnfvlsdkvnsdvklsvndlsvsdklvnlskvnsdkvnsdnvklsvnksdnvkldsnvsdkvnsdlnvsd");
        CapabiltyService capServ = new CapabiltyService(capabilityValidator);

        Mockito.when(capabilityValidator.updateCapabilityValidator(capReq)).thenReturn("capability name cannot be anymore than 20 characters");
        boolean result = capServ.updateCapability(capReq);

        Mockito.verify(capabilityValidator).updateCapabilityValidator(capReq);
        assertEquals(false, result);
    }

    @Test
    public void testServiceUpdateCapabiltyValidatorRetrunsErrorForSpaces() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);

        CapabilityValidator capabilityValidator = Mockito.mock(CapabilityValidator.class);
        Capabilities capReq = new Capabilities(1, " letters");
        CapabiltyService capServ = new CapabiltyService(capabilityValidator);

        Mockito.when(capabilityValidator.updateCapabilityValidator(capReq)).thenReturn("capability name cannot contain empty spaces");
        boolean result = capServ.updateCapability(capReq);

        Mockito.verify(capabilityValidator).updateCapabilityValidator(capReq);
        assertEquals(false, result);
    }

    @Test
    public void testServiceUpdateCapabilty() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);
        CapabilityValidator capabilityValidator = Mockito.mock(CapabilityValidator.class);

        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
        Capabilities capReq = new Capabilities(1, "Engineering");
        CapabiltyService capServ = new CapabiltyService(capabilityDAO, connector, capabilityValidator);

        Mockito.when(capabilityValidator.updateCapabilityValidator(capReq)).thenReturn(null);
        Mockito.when(capabilityDAO.updateCapability(connection, capReq)).thenReturn(true);
        boolean result = capServ.updateCapability(capReq);

        Mockito.verify(capabilityDAO).updateCapability(connection, capReq);
        assertEquals(true, result);
    }


}
