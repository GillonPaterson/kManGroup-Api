package service;

import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobFamiliesDAO;
import com.kainos.ea.model.JobFamilyModel;
import com.kainos.ea.model.JobFamilyRequestModel;
import com.kainos.ea.service.JobFamiliesService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobFamiliesServiceTest {
    @Test
    void testGetJobFamiliesCallsDAOAndCreatesRequestModel() throws SQLException{
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        List<String> capabilities = new ArrayList<>();
        capabilities.add("Engineering");
        capabilities.add("Cyber Security");

        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
        Mockito.when(capabilityDAO.getJobCapabilitiesFromDatabase(connection)).thenReturn(capabilities);

        List<JobFamilyModel> jobFamilyModels = new ArrayList<>();
        JobFamilyModel one = new JobFamilyModel("Engineering", "Testing");
        JobFamilyModel two = new JobFamilyModel("Cyber Security", "Security");
        jobFamilyModels.add(one);
        jobFamilyModels.add(two);

        JobFamiliesDAO jobFamiliesDAO = Mockito.mock(JobFamiliesDAO.class);
        Mockito.when(jobFamiliesDAO.getFamilies(connection)).thenReturn(jobFamilyModels);

        JobFamiliesService jobFamiliesService = new JobFamiliesService(jobFamiliesDAO, capabilityDAO, connector);

        JobFamilyRequestModel returned = jobFamiliesService.getJobFamilies();

        JobFamilyRequestModel expected = new JobFamilyRequestModel(capabilities, jobFamilyModels);

        assertEquals(expected.getJobFamilyModels(), returned.getJobFamilyModels());
        assertEquals(expected.getCapabilities(), returned.getCapabilities());

    }
}
