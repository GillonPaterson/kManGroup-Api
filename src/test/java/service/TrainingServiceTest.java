package service;

import com.kainos.ea.data.TrainingDAO;
import com.kainos.ea.model.JobTraining;
import com.kainos.ea.service.TrainingService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingServiceTest {

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

        TrainingDAO trainingDAO = Mockito.mock(TrainingDAO.class);
        Mockito.when(trainingDAO.getJobTrainingFromDatabase(connection, "Associate")).thenReturn(jobTraining);

        TrainingService trainingService = new TrainingService(connector, trainingDAO);
        List<JobTraining> returnedList = trainingService.getJobTraining("Associate");

        Mockito.verify(connector).getConnection();
        Mockito.verify(trainingDAO).getJobTrainingFromDatabase(connection, "Associate");

        assertEquals(jobTraining, returnedList);
    }
}
