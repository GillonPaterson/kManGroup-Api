package service;

import com.kainos.ea.data.BandLevelDAO;
import com.kainos.ea.data.CompetencyDAO;
import com.kainos.ea.data.TrainingDAO;
import com.kainos.ea.model.BandLevelModel;
import com.kainos.ea.model.CreateBandLevelRequestModel;
import com.kainos.ea.model.DatabaseUserModel;
import com.kainos.ea.service.BandLevelService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.BandLevelValidator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

public class BandLevelServiceTest {
    int[] validComp = {1, 2};
    int[] validTraining = {1, 2};

    @Test
    void testCreateBandLevelCallsMethods() throws SQLException {
        BandLevelModel validBandLevelModel = new BandLevelModel(" Valid Test ", 1);
        CreateBandLevelRequestModel validModel = new CreateBandLevelRequestModel(validBandLevelModel, validTraining, validComp);

        DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(databaseConnector.getConnection()).thenReturn(connection);

        BandLevelDAO bandLevelDAO = Mockito.mock(BandLevelDAO.class);
        Mockito.when(bandLevelDAO.getMaxImportance(connection)).thenReturn(4);

        BandLevelValidator bandLevelValidator = Mockito.mock(BandLevelValidator.class);
        doNothing().when(bandLevelValidator).addBandLevelValidator(validModel, 4);

        Mockito.when(bandLevelDAO.updateImportance(eq(connection), anyInt())).thenReturn(true);

        Mockito.when(bandLevelDAO.insertBandLevelData(connection, validBandLevelModel)).thenReturn(99);

        CompetencyDAO competencyDAO = Mockito.mock(CompetencyDAO.class);
        Mockito.when(competencyDAO.insertIntoCompetencies(eq(connection), eq(99),anyInt())).thenReturn(true);

        TrainingDAO trainingDAO = Mockito.mock(TrainingDAO.class);
        Mockito.when(trainingDAO.insertIntoBandLevelsTraining(eq(connection), eq(99),anyInt())).thenReturn(true);

        BandLevelService bandLevelService = new BandLevelService(bandLevelDAO,competencyDAO,trainingDAO,databaseConnector,bandLevelValidator);

        bandLevelService.createBandLevel(validModel);

        Mockito.verify(databaseConnector).getConnection();
        Mockito.verify(bandLevelDAO).getMaxImportance(connection);
        Mockito.verify(bandLevelValidator).addBandLevelValidator(validModel,4 );
        Mockito.verify(bandLevelDAO, times(4)).updateImportance(eq(connection), anyInt());

        Mockito.verify(bandLevelDAO).insertBandLevelData(connection, validBandLevelModel);
        Mockito.verify(competencyDAO, times(2)).insertIntoCompetencies(eq(connection), eq(99),anyInt());
        Mockito.verify(trainingDAO, times(2)).insertIntoBandLevelsTraining(eq(connection), eq(99),anyInt());
    }
}
