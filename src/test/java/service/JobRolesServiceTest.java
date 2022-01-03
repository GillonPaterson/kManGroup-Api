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
    void testServicegetComp() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        Competency competencyModel = new Competency("Associate",
                "Reflects on owninteractions with a wide and diverse range of individuals andgroups from within and beyond immediate service/organisation.Challenges and refreshes own values, beliefs, leadership styles and approaches. Overtly role models the giving and receiving of feedback.",
                "'Successfully manages a range of personal and organisational demandsand pressures. Demonstrates tenacity and resilience. Overcomes setbackswhere goals cannot be achieved and quickly refocuses. Is visible andaccessible to others.'",
                "Develops through systematically scanningthe external environment and exploring leading edge thinking and best practice.Applies learning to build and refresh the business. Treats challenge as a positive forcefor improvement.",
                null,
                "stage 3");

        Competency competencyModelService = new Competency("Associate", "Reflects on owninteractions with a wide and diverse range of individuals andgroups from within and beyond immediate service/organisation.Challenges and refreshes own values, beliefs, leadership styles and approaches. Overtly role models the giving and receiving of feedback.", "'Successfully manages a range of personal and organisational demandsand pressures. Demonstrates tenacity and resilience. Overcomes setbackswhere goals cannot be achieved and quickly refocuses. Is visible andaccessible to others.'", "Develops through systematically scanningthe external environment and exploring leading edge thinking and best practice.Applies learning to build and refresh the business. Treats challenge as a positive forcefor improvement.");


        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobCompFromDatabase(connection, 1)).thenReturn(competencyModel);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        Competency returnedModel = jobRolesService.getComp(1);

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobCompFromDatabase(connection, 1);
        assertEquals(competencyModelService.getBandLevel(), returnedModel.getBandLevel());
        assertEquals(competencyModelService.getCompetencyStage1(), returnedModel.getCompetencyStage1());
        assertEquals(competencyModelService.getCompetencyStage2(), returnedModel.getCompetencyStage2());
        assertEquals(competencyModelService.getCompetencyStage3(), returnedModel.getCompetencyStage3());
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

    /*

    @Test
    void testServiceGetTrainingDPDAO() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);
        JobTraining jt1 = new JobTraining("Associate", "Mindset", "https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Mindset.aspx", "Development programmes");
        JobTraining jt2 = new JobTraining("Associate", "Intro to Remote Working", "https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Intro-to-Remote-Working.aspx", "Development programmes");


        List<JobTraining> jobTraining = new ArrayList<>();
        jobTraining.add(jt1);
        jobTraining.add(jt2);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobTrainingDPFromDatabase(connection, "Associate")).thenReturn(jobTraining);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        List<JobTraining> returnedList = jobRolesService.getJobTrainingDP("Associate");

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobTrainingDPFromDatabase(connection, "Associate");

        assertEquals(jobTraining, returnedList);
    }

    @Test
    void testServiceGetTrainingPSDAO() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);
        JobTraining jt1 = new JobTraining("Associate", "Interpersonal Skills", "https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Interpersonal-Skills.aspx", "Professional skills");
        JobTraining jt2 = new JobTraining("Associate", "Developing your Presentation Skills", "https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/Developing-your-Presentation-Skills.aspx", "Professional skills");


        List<JobTraining> jobTraining = new ArrayList<>();
        jobTraining.add(jt1);
        jobTraining.add(jt2);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobTrainingPSFromDatabase(connection, "Associate")).thenReturn(jobTraining);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        List<JobTraining> returnedList = jobRolesService.getJobTrainingPS("Associate");

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobTrainingPSFromDatabase(connection, "Associate");

        assertEquals(jobTraining, returnedList);
    }

    @Test
    void testServiceGetTrainingTSDAO() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);
        JobTraining jt1 = new JobTraining("Associate", "Powerpoint 101", "https://kainossoftwareltd.sharepoint.com/L%26D/SitePages/PowerPoint-101.aspx", "Technical skills");


        List<JobTraining> jobTraining = new ArrayList<>();
        jobTraining.add(jt1);

        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
        Mockito.when(jobRolesDAO.getJobTrainingTSFromDatabase(connection, "Associate")).thenReturn(jobTraining);

        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, connector);
        List<JobTraining> returnedList = jobRolesService.getJobTrainingTS("Associate");

        Mockito.verify(connector).getConnection();
        Mockito.verify(jobRolesDAO).getJobTrainingTSFromDatabase(connection, "Associate");

        assertEquals(jobTraining, returnedList);
    }

     
//    @Test
//    void testServiceCallsRightDAOAndReturnMatrix() throws SQLException{
//        Connection connection = Mockito.mock(Connection.class);
//        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
//        Mockito.when(connector.getConnection()).thenReturn(connection);
//
//        List<String> capabilities = new ArrayList<>();
//        capabilities.add("Engineering");
//        capabilities.add("Platforms");
//
//        CapabilityDAO capabilityDAO = Mockito.mock(CapabilityDAO.class);
//        Mockito.when(capabilityDAO.getJobCapabilitiesFromDatabase(connection)).thenReturn(capabilities);
//
//        List<String> bandLevels = new ArrayList<>();
//        bandLevels.add("Apprentice");
//        bandLevels.add("Trainee");
//
//        BandLevelDAO bandLevelDAO = Mockito.mock(BandLevelDAO.class);
//        Mockito.when(bandLevelDAO.getBandLevelFromDatabase(connection)).thenReturn(bandLevels);
//
//        List<RoleMatrixModel> roleMatrixModels = new ArrayList<>();
//        RoleMatrixModel roleMatrixModel1 = new RoleMatrixModel("Software Dev", "Engineering", "Apprentice", "1");
//        RoleMatrixModel roleMatrixModel2 = new RoleMatrixModel("Platform Engineer", "Platforms", "Trainee", "2");
//        roleMatrixModels.add(roleMatrixModel1);
//        roleMatrixModels.add(roleMatrixModel2);
//
//        JobRolesDAO jobRolesDAO = Mockito.mock(JobRolesDAO.class);
//        Mockito.when(jobRolesDAO.getJobRoleMatrixFromDatabase(connection)).thenReturn(roleMatrixModels);
//
//        JobRolesService jobRolesService = new JobRolesService(jobRolesDAO, bandLevelDAO, capabilityDAO, connector);
//
//        String[][] returnedArray = jobRolesService.getRoleMatrix();
//
//        Mockito.verify(connector).getConnection();
//        Mockito.verify(capabilityDAO).getJobCapabilitiesFromDatabase(connection);
//        Mockito.verify(bandLevelDAO).getBandLevelFromDatabase(connection);
//        Mockito.verify(jobRolesDAO).getJobRoleMatrixFromDatabase(connection);
//
//        String[][] expectedArray = {{"Job Band Level", "Engineering", "Platforms"}, {"Apprentice", "Software Dev;1", ""}, {"Trainee", "", "Platform Engineer;2"}};
////        System.out.println(Arrays.deepToString(returnedArray));
////        System.out.println(Arrays.deepToString(expectedArray));
//        assertArrayEquals(expectedArray,returnedArray);
//    }
}
