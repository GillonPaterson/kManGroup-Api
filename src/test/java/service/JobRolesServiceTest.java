package service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.*;
import com.kainos.ea.service.JobRolesService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.kainos.ea.service.CapabiltyService;

import javax.annotation.meta.When;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JobRolesServiceTest {
    @Test
    void testServiceGetRolesCallsDAO() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        JobRole jobRole1 = new JobRole(1, "Dev", "Engineering", "Associate");
        JobRole jobRole2 = new JobRole(2, "Tester", "Engineering", "Apprentice");

        List<JobRole> jobRoles = new ArrayList<>();
        jobRoles.add(jobRole1);
        jobRoles.add(jobRole2);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobRolesFromDatabase(connection)).thenReturn(jobRoles);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        List<JobRole> returnedList = jobRolesService.getJobRoles();

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobRolesFromDatabase(connection);

        assertEquals(jobRoles, returnedList);
    }

    @Test
    void testServiceGetCompetency() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        List competencies = new ArrayList();
        competencies.add("Behaves in an open, honest, and inclusive manner, upholding personal andorganisational ethics and values. Shows respect for the needs of others and promotes equality and diversity.");
        competencies.add("Confident and independent in own personal impact and recognises an influence on others beyond immediate teams. Goals are aligned to strategic objectives and Kainos values. Champions self and others for equality, diversity, and inclusion.");
        Competency comp1 = new Competency("Associate",competencies);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobCompFromDatabase(connection,1)).thenReturn(comp1);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        Competency returnedList = jobRolesService.getComp(1);

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobCompFromDatabase(connection,1);

        assertEquals(comp1, returnedList);
    }

    @Test
    void testServiceGetSpecCallsDAO() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        JobSpecModel jobSpecModel = new JobSpecModel("Dev", "Test Spec", "Test Link", "Test Responsibility");

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobSpecFromDatabase(connection, 1)).thenReturn(jobSpecModel);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        JobSpecModel returnedModel = jobRolesService.getJobSpec(1);

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobSpecFromDatabase(connection, 1);

        assertEquals(jobSpecModel, returnedModel);
    }

    @Test
    void testServiceGetTrainingDAO() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);
        JobTraining jt1 = new JobTraining("Associate", "Mindset", "https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Mindset.aspx", "Development programmes");
        JobTraining jt2 = new JobTraining("Associate", "Intro to Remote Working", "https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Intro-to-Remote-Working.aspx", "Development programmes");


        List<JobTraining> jobTraining = new ArrayList<>();
        jobTraining.add(jt1);
        jobTraining.add(jt2);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobTrainingFromDatabase(connection, "Associate")).thenReturn(jobTraining);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        List<JobTraining> returnedList = jobRolesService.getJobTraining("Associate");

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobTrainingFromDatabase(connection, "Associate");

        assertEquals(jobTraining, returnedList);
    }


    @Test
    void testServiceCallsRightDAOAndReturnMatrix() throws SQLException{
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        List<String> capabilities = new ArrayList<>();
        capabilities.add("Engineering");
        capabilities.add("Platforms");

        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
        Mockito.when(capabilityDAO.getJobCapabilitiesFromDatabase(connection)).thenReturn(capabilities);

        List<String> bandLevels = new ArrayList<>();
        bandLevels.add("Apprentice");
        bandLevels.add("Trainee");

        BandLevelDAO bandLevelDAO = Mockito.mock(BandLevelDAO.class);
        Mockito.when(bandLevelDAO.getBandLevelFromDatabase(connection)).thenReturn(bandLevels);

        List<RoleMatrixModel> roleMatrixModels = new ArrayList<>();
        RoleMatrixModel roleMatrixModel1 = new RoleMatrixModel("Software Dev", "Engineering", "Apprentice", "1");
        RoleMatrixModel roleMatrixModel2 = new RoleMatrixModel("Platform Engineer", "Platforms", "Trainee", "2");
        roleMatrixModels.add(roleMatrixModel1);
        roleMatrixModels.add(roleMatrixModel2);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobRoleMatrixFromDatabase(connection)).thenReturn(roleMatrixModels);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, bandLevelDAO, capabilityDAO, connector);

        RoleMatrixResponseModel returnedResponse = jobRolesService.getRoleMatrix();

        Mockito.verify(connector).getConnection();
        Mockito.verify(capabilityDAO).getJobCapabilitiesFromDatabase(connection);
        Mockito.verify(bandLevelDAO).getBandLevelFromDatabase(connection);
        Mockito.verify(jobRolesDAO).getJobRoleMatrixFromDatabase(connection);

        RoleMatrixResponseModel expected = new RoleMatrixResponseModel(roleMatrixModels, bandLevels, capabilities);
        assertEquals(expected.roleMatrixModel,returnedResponse.roleMatrixModel);
        assertEquals(expected.capability,returnedResponse.capability);
        assertEquals(expected.bandLevel,returnedResponse.bandLevel);
    }
}
