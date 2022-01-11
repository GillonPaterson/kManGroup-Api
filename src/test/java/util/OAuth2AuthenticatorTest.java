package util;

import com.kainos.ea.util.OAuth2Authenticator;
import com.kainos.ea.util.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OAuth2AuthenticatorTest {
    //Will have to make a new one in 04/10/2129 as expires then
    static String adminToken;
    static String expiredToken;
    static String nonAdminToken;
    static String invalidToken ;

    @BeforeAll
    static void setUpTokens(){
        try {
            FileInputStream propsStream =
                    new FileInputStream("authTest.properties");

            Properties props = new Properties();
            props.load(propsStream);

            adminToken = props.getProperty("adminToken");
            nonAdminToken = props.getProperty("nonAdminToken");
            expiredToken = props.getProperty("expiredToken");
            invalidToken = props.getProperty("invalidToken");

            if (adminToken == null || nonAdminToken == null || expiredToken == null || invalidToken == null)
                throw new IllegalArgumentException(
                        "Properties file must exist and must contain "
                                + "all tokens for tests to work.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testReturnsUserPrincipleObjectWithAdminWhenGivenAdminToken(){

        Set<String> expectedRolesSet = new HashSet<>();
        expectedRolesSet.add("Admin");

        User expectedUser = new User("admin", expectedRolesSet);

        OAuth2Authenticator oAuth2Authenticator = new OAuth2Authenticator();
        Optional<User> returnedOptional = oAuth2Authenticator.authenticate(adminToken);

        assertTrue(returnedOptional.isPresent());
        User returned = returnedOptional.get();
        assertEquals(expectedUser.getName(), returned.getName());
        assertEquals(expectedUser.getRoles(), returned.getRoles());
    }

    @Test
    void testReturnsUserPrincipleObjectWithNonAdminWhenGivenNonAdminToken(){

        Set<String> expectedRolesSet = new HashSet<>();

        User expectedUser = new User("admin", expectedRolesSet);

        OAuth2Authenticator oAuth2Authenticator = new OAuth2Authenticator();
        Optional<User> returnedOptional = oAuth2Authenticator.authenticate(nonAdminToken);

        assertTrue(returnedOptional.isPresent());
        User returned = returnedOptional.get();
        assertEquals(expectedUser.getName(), returned.getName());
        assertEquals(expectedUser.getRoles(), returned.getRoles());
    }

    @Test
    void testReturnsNothingWhenGivenExpiredToken(){

        OAuth2Authenticator oAuth2Authenticator = new OAuth2Authenticator();
        Optional<User> returnedOptional = oAuth2Authenticator.authenticate(expiredToken);

        assertFalse(returnedOptional.isPresent());
    }

    @Test
    void testReturnsNothingWhenGivenInvalidToken(){

        OAuth2Authenticator oAuth2Authenticator = new OAuth2Authenticator();
        Optional<User> returnedOptional = oAuth2Authenticator.authenticate(invalidToken);

        assertFalse(returnedOptional.isPresent());
    }
}
