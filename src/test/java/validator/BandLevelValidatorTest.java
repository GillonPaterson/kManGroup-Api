package validator;

import com.kainos.ea.data.CompetencyDAO;
import com.kainos.ea.data.TrainingDAO;
import com.kainos.ea.model.BandLevelModel;
import com.kainos.ea.model.CreateBandLevelRequestModel;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.validator.BandLevelValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import javax.validation.ValidationException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class BandLevelValidatorTest {
    static BandLevelValidator bandLevelValidator;
    static int[] validComp = {1, 2};
    static int[] invalidComp = {0, 2};
    static int[] validTraining = {1, 2};
    static int[] invalidTraining = {0, 2};
    static int maxImportance = 4;
    CreateBandLevelRequestModel validModel;

    @BeforeAll
    public static void setupTestClass() throws SQLException {
        DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);
        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(databaseConnector.getConnection()).thenReturn(connection);

        CompetencyDAO competencyDAO = Mockito.mock(CompetencyDAO.class);
        Mockito.when(competencyDAO.checkCompetencyID(connection, validComp)).thenReturn(true);
        Mockito.when(competencyDAO.checkCompetencyID(connection, invalidComp)).thenReturn(false);

        TrainingDAO trainingDAO = Mockito.mock(TrainingDAO.class);
        Mockito.when(trainingDAO.checkTrainingID(connection, validTraining)).thenReturn(true);
        Mockito.when(trainingDAO.checkTrainingID(connection, invalidTraining)).thenReturn(false);

        bandLevelValidator = new BandLevelValidator(trainingDAO, competencyDAO, databaseConnector);
    }

    @BeforeEach
    void setupValidModel() {
        BandLevelModel validBandLevelModel = new BandLevelModel(" Valid Test ", 5);
        validModel = new CreateBandLevelRequestModel(validBandLevelModel, validTraining, validComp);
    }

    @Test
    void testAddBandLevelValidatorValidIsValid() throws SQLException {
        try {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        } catch (ValidationException validationException) {
            fail("Valid not valid");
        }
    }

    @Test
    void testAddBandLevelValidatorImportanceTooLow() {
        validModel.getBandLevel().setImportance(0);
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Importance too high or low";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testAddBandLevelValidatorImportanceTooHigh() {
        validModel.getBandLevel().setImportance(6);
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Importance too high or low";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4, 5})
    void testAddBandLevelValidatorImportanceValidEdgeCases(int importance) throws SQLException {
        validModel.getBandLevel().setImportance(importance);
        bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
    }

    @Test
    void testAddBandLevelValidatorBandLevelContainsSpecialCharacters() {
        validModel.getBandLevel().setJobBandLevel(" Test bAN!D ");
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Band Level must only contain letters and spaces";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testAddBandLevelValidatorBandLevelContainsNumbers() {
        validModel.getBandLevel().setJobBandLevel(" Test b4ND ");
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Band Level must only contain letters and spaces";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testAddBandLevelValidatorTrainingArrayEmpty() {
        int[] emptyArray = {};
        validModel.setTraining(emptyArray);
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Training must have at least one item in it and no greater than 6";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testAddBandLevelValidatorTrainingArrayTooBig() {
        int[] emptyArray = {1, 2, 3, 4, 5, 6, 7};
        validModel.setTraining(emptyArray);
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Training must have at least one item in it and no greater than 6";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testAddBandLevelValidatorCompetencyArrayEmpty() {
        int[] emptyArray = {};
        validModel.setCompetencies(emptyArray);
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Competencies must have at least one item in it and no greater than 6";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testAddBandLevelValidatorCompetencyArrayTooBig() {
        int[] bigArray = {1, 2, 3, 4, 5, 6, 7};
        validModel.setCompetencies(bigArray);
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Competencies must have at least one item in it and no greater than 6";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testAddBandLevelValidatorTrainingArrayContainsInvalidID() {
        validModel.setTraining(invalidTraining);
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Some Training id's don't exist";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testAddBandLevelValidatorCompetencyArrayContainsInvalidID() {
        validModel.setCompetencies(invalidComp);
        Exception exception = assertThrows(ValidationException.class, () -> {
            bandLevelValidator.addBandLevelValidator(validModel, maxImportance);
        });
        String expectedMessage = "Some Competency id's don't exist";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
