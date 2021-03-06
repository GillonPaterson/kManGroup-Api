package service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CapabilityDAO;
import com.kainos.ea.data.JobRolesDAO;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecModel;
import com.kainos.ea.model.RoleMatrixModel;
import com.kainos.ea.model.RoleMatrixResponseModel;
import com.kainos.ea.model.AddJobRole;
import com.kainos.ea.model.EditJobRole;

import com.kainos.ea.service.JobRolesService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.JobRoleValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class JobRolesServiceTest {
    @Test
    void testServiceGetRolesCallsDAO() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        JobRole jobRole1 = new JobRole(1, "Dev", "Engineering", "Associate", "Engineering");
        JobRole jobRole2 = new JobRole(2, "Tester", "Engineering", "Apprentice", "Engineering");

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
    void testServiceGetRolesFilterCallsDAO() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        JobRole jobRole1 = new JobRole(1, "Dev", "Engineering", "Associate", "Engineering");
        JobRole jobRole2 = new JobRole(2, "Tester", "Engineering", "Apprentice", "Engineering");

        List<JobRole> jobRoles = new ArrayList<>();
        jobRoles.add(jobRole1);
        jobRoles.add(jobRole2);

        List<String> capabilityFilters = new ArrayList<>();
        capabilityFilters.add("Engineering");
        List<String> familyFilters = new ArrayList<>();
        familyFilters.add("Engineering");
        List<String> bandLevelFilters = new ArrayList<>();
        bandLevelFilters.add("Associate");
        String nameFilter = "";
        String query = "SELECT jobRoleID, jobRole, jobFamilyName, jobCapability, jobBandLevel FROM jobRoles Inner join jobFamilies using(jobFamilyID) inner join capabilities using(jobCapabilityID) inner join bandLevels using (jobBandLevelID) where (jobCapability = ?) and (jobBandLevel = ?) and (jobFamilyName = ?) and (jobRole LIKE ?)";

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobRolesFromDatabaseWithFilter(connection, capabilityFilters, familyFilters, bandLevelFilters, nameFilter, query)).thenReturn(jobRoles);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        List<JobRole> returnedList = jobRolesService.getJobRolesFilter(capabilityFilters, familyFilters, bandLevelFilters, nameFilter);

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobRolesFromDatabaseWithFilter(connection, capabilityFilters, familyFilters, bandLevelFilters, nameFilter, query);

        assertEquals(jobRoles, returnedList);
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
    void testServiceCallsRightDAOAndReturnMatrix() throws SQLException {
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
        assertEquals(expected.roleMatrixModel, returnedResponse.roleMatrixModel);
        assertEquals(expected.capability, returnedResponse.capability);
        assertEquals(expected.bandLevel, returnedResponse.bandLevel);
    }


    @Test
    public void addJobRoleTest() {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRolesValidator = Mockito.mock(JobRoleValidator.class);
        AddJobRole job = new AddJobRole();
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRolesValidator);

        Mockito.when(jobRolesValidator.addJobRoleValidator(job)).thenReturn(null);
        Mockito.when(jobRolesDAO.addJobRole(connection, job)).thenReturn(20);

        int result = jobServ.addJobRole(job);

        Mockito.verify(jobRolesDAO).addJobRole(connection, job);
        assertEquals(20, result);
    }


    @Test
    void testServiceGetRoleCallsDAO() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        EditJobRole jobRole1 = new EditJobRole(1, "Dev", "Engineering", "Associate", "Engineering", "https://test", "test");

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobRoleFromDatabase(connection, 1)).thenReturn(jobRole1);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        EditJobRole returned = jobRolesService.getJobRole(1);

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobRoleFromDatabase(connection, 1);

        assertEquals(jobRole1, returned);
    }

    @Test
    public void editJobRoleTest() {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRolesValidator = Mockito.mock(JobRoleValidator.class);
        AddJobRole editJob = new AddJobRole();
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRolesValidator);

        Mockito.when(jobRolesValidator.addJobRoleValidator(editJob)).thenReturn(null);
        Mockito.when(jobRolesDAO.editJobRole(connection, editJob, 1)).thenReturn(1);

        int result = jobServ.editJobRole(editJob, 1);

        Mockito.verify(jobRolesDAO).editJobRole(connection, editJob, 1);
        assertEquals(1, result);
    }


    @Test
    public void deleteJobRoleTest() {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector);

        Mockito.when(jobRolesDAO.deleteJobRole(connection, 1)).thenReturn(1);

        int result = -1;

        try {
            result = jobServ.deleteJobRole(1);
        } catch (SQLException e) {
            System.out.println(e);
        }


        Mockito.verify(jobRolesDAO).deleteJobRole(connection, 1);
        assertEquals(1, result);
    }


    @Test
    public void testServiceAddRoleValidatorReturnsErrorForNumbersInName() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("12345", "test", "test", "https://test", "test", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The role name cannot contain numbers");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    public void testServiceAddRoleValidatorReturnsErrorForTooManyCharacters() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh", "test", "test", "https://test", "test", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The role name cannot be anymore than 40 characters");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    public void testServiceAddRoleValidatorReturnsErrorForSpaces() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole(" test", "test", "test", "https://test", "test", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The role name cannot contain empty spaces at the start and end");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    public void testServiceAddRoleValidatorReturnsErrorForSpecNotBeingEntered() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("test", "test", "", "https://test", "test", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The job specification must be entered");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }


    @Test
    public void testServiceAddRoleValidatorReturnsErrorForSpecBeingTooLong() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("test", "test", "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. testestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestestWhat is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. testestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestest", "https://test", "test", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The job specification cannot be anymore than 1000 characters");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }


    @Test
    public void testServiceAddRoleValidatorReturnsErrorForRespNotBeingEntered() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("test", "test", "test", "https://test", "", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The job responsibilities must be entered");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test

    public void testServiceAddRoleValidatorReturnsErrorForRespBeingTooLong() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("test", "test", "test", "https://test", "What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. testestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestestWhat is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. testestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestest", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The job responsibilities cannot be anymore than 1000 characters");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    public void testServiceAddRoleValidatorReturnsErrorForLinkNotBeingHTTPS() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("test", "test", "test", "test", "", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The link must start with 'https://'");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    public void testServiceAddRoleValidatorReturnsErrorForLinkNotBeingLongerThan8() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("test", "test", "test", "https://", "", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("A link must be entered");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }

    @Test
    public void testServiceAddRoleValidatorReturnsErrorForLinkBeingTooLong() throws SQLException {
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        JobRoleValidator jobRoleValidator = Mockito.mock(JobRoleValidator.class);

        AddJobRole addJobRole = new AddJobRole("test", "test", "test", "https://What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum. testestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestesttestestestestest", "", "test");
        JobRolesService jobServ = new JobRolesService(jobRolesDAO, connector, jobRoleValidator);

        Mockito.when(jobRoleValidator.addJobRoleValidator(addJobRole)).thenReturn("The link cannot be anymore than 500 characters");
        int result = jobServ.addJobRole(addJobRole);

        Mockito.verify(jobRoleValidator).addJobRoleValidator(addJobRole);
        System.out.println(result);
        assertEquals(0, result);
    }
}
