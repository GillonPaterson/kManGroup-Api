package service;

import com.kainos.ea.data.JobFamiliesDAO;
import com.kainos.ea.model.JobFamilyModel;
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

        List<String> families1 = new ArrayList<>();
        families1.add("Engineering");
        families1.add("Testing");

        List<String> families2 = new ArrayList<>();
        families2.add("Security");

        List<JobFamilyModel> jobFamilyModels = new ArrayList<>();
        JobFamilyModel one = new JobFamilyModel("Engineering", families1);
        JobFamilyModel two = new JobFamilyModel("Cyber Security", families2);
        jobFamilyModels.add(one);
        jobFamilyModels.add(two);

        JobFamiliesDAO jobFamiliesDAO = Mockito.mock(JobFamiliesDAO.class);
        Mockito.when(jobFamiliesDAO.getFamilies(connection)).thenReturn(jobFamilyModels);

        JobFamiliesService jobFamiliesService = new JobFamiliesService(jobFamiliesDAO, connector);

        List<JobFamilyModel> returned = jobFamiliesService.getJobFamilies();

        assertEquals(jobFamilyModels, returned);

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobFamiliesDAO).getFamilies(connection);

    }
}
