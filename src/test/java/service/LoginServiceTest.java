package service;

import com.kainos.ea.data.LoginDAO;
import com.kainos.ea.model.DatabaseUserModel;
import com.kainos.ea.model.TokenSubject;
import com.kainos.ea.model.UserRequestModel;
import com.kainos.ea.service.LoginService;
import com.kainos.ea.util.DatabaseConnector;
import com.kainos.ea.util.Hasher;
import com.kainos.ea.util.TokenHandler;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;

public class LoginServiceTest {
    @Test
    void testCheckDetailsReturnsATokenIfDetailsCorrect() throws Exception {
        String[] roles = {};
        UserRequestModel userRequestModel = new UserRequestModel(roles,"test","password");
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        DatabaseUserModel returnedUser = new DatabaseUserModel("test","EJWIwrRQj/M6bdG5Q8dM0Q==","q6kuEzVuvwqiYkYVm8JGURhC8cQ9lKt5nPdoqOYoB/JE4zN1eI79ZW7BF2I+Slqh5gfOcef67WTgMr8H1kG6qA==", true);

        LoginDAO loginDAO = Mockito.mock(LoginDAO.class);
        Mockito.when(loginDAO.getDetails(connection,"test")).thenReturn(returnedUser);

        Hasher hasher = Mockito.mock(Hasher.class);
        Mockito.when(hasher.hashPassword(userRequestModel.getPassword(),"q6kuEzVuvwqiYkYVm8JGURhC8cQ9lKt5nPdoqOYoB/JE4zN1eI79ZW7BF2I+Slqh5gfOcef67WTgMr8H1kG6qA==")).thenReturn("EJWIwrRQj/M6bdG5Q8dM0Q==");


        String expectedToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjAsInVzZXJuYW1lIjoidGVzdCIsImlzQWRtaW4iOmZhbHNlLCJleHAiOjB9.xjxQ4_GJjbrS5HmV7iwhvuelEZIf82uxBpyzz_jAkRE";
        TokenHandler tokenHandler = Mockito.mock(TokenHandler.class);
        Mockito.when(tokenHandler.createToken(any(TokenSubject.class), eq(TimeUnit.HOURS.toMillis(1)))).thenReturn(expectedToken);

        LoginService loginService = new LoginService(loginDAO, connector, hasher, tokenHandler);

        String returnedToken = loginService.checkDetails(userRequestModel);

        Mockito.verify(connector).getConnection();
        Mockito.verify(loginDAO).getDetails(connection,"test");
        Mockito.verify(hasher).hashPassword(userRequestModel.getPassword(), returnedUser.getSalt());
        Mockito.verify(tokenHandler).createToken(any(TokenSubject.class), eq(TimeUnit.HOURS.toMillis(1)));

        assertEquals(expectedToken, returnedToken);
    }

    @Test
    void testCheckDetailsReturnsNullIfPasswordIncorrect() throws Exception {
        String[] roles = {};
        UserRequestModel userRequestModel = new UserRequestModel(roles,"test","password");
        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        DatabaseUserModel returnedUser = new DatabaseUserModel("test","EJWIwrRQj/M6bdG5Q8dM0Q==","q6kuEzVuvwqiYkYVm8JGURhC8cQ9lKt5nPdoqOYoB/JE4zN1eI79ZW7BF2I+Slqh5gfOcef67WTgMr8H1kG6qA==", true);

        LoginDAO loginDAO = Mockito.mock(LoginDAO.class);
        Mockito.when(loginDAO.getDetails(connection,"test")).thenReturn(returnedUser);

        Hasher hasher = Mockito.mock(Hasher.class);
        Mockito.when(hasher.hashPassword(userRequestModel.getPassword(),"q6kuEzVuvwqiYkYVm8JGURhC8cQ9lKt5nPdoqOYoB/JE4zN1eI79ZW7BF2I+Slqh5gfOcef67WTgMr8H1kG6qA==")).thenReturn("EJWIwrRQj/M6bdG5Q8dP0Q==");

        LoginService loginService = new LoginService(loginDAO, connector, hasher);

        Exception exception = assertThrows(SQLException.class, () -> {
            loginService.checkDetails(userRequestModel);
        });
        assertEquals("Passwords don't match", exception.getMessage());
        Mockito.verify(connector).getConnection();
        Mockito.verify(loginDAO).getDetails(connection,"test");
        Mockito.verify(hasher).hashPassword(userRequestModel.getPassword(), returnedUser.getSalt());
    }

    @Test
    void testRegisterUserCreatesSaltAndHashesPasswordThenInsertsIntoDAO() throws Exception{
        String[] roles = {"Admin"};
        UserRequestModel userRequestModel = new UserRequestModel(roles,"test","password");

        Connection connection = Mockito.mock(Connection.class);
        DatabaseConnector connector = Mockito.mock(DatabaseConnector.class);
        Mockito.when(connector.getConnection()).thenReturn(connection);

        Hasher hasher = Mockito.mock(Hasher.class);
        Mockito.when(hasher.getNewSalt()).thenReturn("q6kuEzVuvwqiYkYVm8JGURhC8cQ9lKt5nPdoqOYoB/JE4zN1eI79ZW7BF2I+Slqh5gfOcef67WTgMr8H1kG6qA==");
        Mockito.when(hasher.hashPassword(userRequestModel.getPassword(),"q6kuEzVuvwqiYkYVm8JGURhC8cQ9lKt5nPdoqOYoB/JE4zN1eI79ZW7BF2I+Slqh5gfOcef67WTgMr8H1kG6qA==")).thenReturn("EJWIwrRQj/M6bdG5Q8dP0Q==");

        LoginDAO loginDAO = Mockito.mock(LoginDAO.class);
        doNothing().when(loginDAO).registerUser(eq(connection), any(DatabaseUserModel.class));

        LoginService loginService = new LoginService(loginDAO, connector, hasher);
        loginService.registerUser(userRequestModel);

        Mockito.verify(connector).getConnection();
        Mockito.verify(hasher).getNewSalt();
        Mockito.verify(hasher).hashPassword(userRequestModel.getPassword(),"q6kuEzVuvwqiYkYVm8JGURhC8cQ9lKt5nPdoqOYoB/JE4zN1eI79ZW7BF2I+Slqh5gfOcef67WTgMr8H1kG6qA==");
        ArgumentCaptor<DatabaseUserModel> argument = ArgumentCaptor.forClass(DatabaseUserModel.class);
        Mockito.verify(loginDAO).registerUser(eq(connection),argument.capture());
        DatabaseUserModel captured = argument.getValue();
        assertEquals("test", captured.getUsername());
        assertEquals("EJWIwrRQj/M6bdG5Q8dP0Q==", captured.getPasswordHash());
        assertEquals("q6kuEzVuvwqiYkYVm8JGURhC8cQ9lKt5nPdoqOYoB/JE4zN1eI79ZW7BF2I+Slqh5gfOcef67WTgMr8H1kG6qA==", captured.getSalt());
        assertTrue(captured.isAdmin());
    }
}
