package service;

import com.kainos.ea.data.CompetencyDAO;
import com.kainos.ea.model.Competency;
import com.kainos.ea.service.CompetencyService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CompetencyServiceTest {

    @Test
    void testServiceGetCompetency() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        List competencies = new ArrayList();
        competencies.add("Behaves in an open, honest, and inclusive manner, upholding personal and organisational ethics and values. Shows respect for the needs of others and promotes equality and diversity.");
        competencies.add("Confident and independent in own personal impact and recognises an influence on others beyond immediate teams. Goals are aligned to strategic objectives and Kainos values. Champions self and others for equality, diversity, and inclusion.");
        Competency comp1 = new Competency("Associate",competencies);

        CompetencyDAO competencyDAO = Mockito.mock(CompetencyDAO.class);
        Mockito.when(competencyDAO.getJobCompFromDatabase(connection,1)).thenReturn(comp1);

        CompetencyService competencyService = new CompetencyService(competencyDAO, connector);
        Competency returnedList = competencyService.getComp(1);

        Mockito.verify(connector).getConnection();
        Mockito.verify(competencyDAO).getJobCompFromDatabase(connection,1);

        assertEquals(comp1, returnedList);
    }
}
