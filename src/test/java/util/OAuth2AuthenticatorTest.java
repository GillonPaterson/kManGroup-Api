package util;

import com.kainos.ea.util.OAuth2Authenticator;
import com.kainos.ea.util.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OAuth2AuthenticatorTest {
    //Will have to make a new one in 04/10/2129 as expires then
    //Should store these securely
    String adminToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDE4OTg4NzcsInVzZXJuYW1lIjoiYWRtaW4iLCJpc0FkbWluIjp0cnVlLCJleHAiOjUwNDE1MDA0Nzd9.XanyBYN7Lxe1kh54dBy5vgwV3owYFTr78GgE0EmMcFs";
    String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDE4OTg4NzcsInVzZXJuYW1lIjoiYWRtaW4iLCJpc0FkbWluIjp0cnVlLCJleHAiOjE2NDE1MDA0Nzd9.mMHpvTbBQgDugSX7DlJ4pxopnvLiyFucX-Y2BmU-w34";
    String nonAdminToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDE4OTg4NzcsInVzZXJuYW1lIjoiYWRtaW4iLCJpc0FkbWluIjpmYWxzZSwiZXhwIjo1MDQxNTAwNDc3fQ.vZOAnZO5DSWHd17LyXF2zSot7GvkobpOCNJnIffnuKM";
    String invalidToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDE4OTg4NzcsInVzZXJuYW1lIjoiYWRtaW4iLCJpc0FkbWluIjp0cnVlLCJleHAiOjUwNDE1MDA0Nzd9.oGJnhsJ2WQlrx9ispBx0cl7t7i1cQ8vcq_qzo_bfBB";

    //How to store securely but then how pipeline
//    @BeforeAll
//    static void setUpTokens(){
//        try {
//            FileInputStream propsStream =
//                    new FileInputStream("authTest.properties");
//
//            Properties props = new Properties();
//            props.load(propsStream);
//
//            adminToken = props.getProperty("adminToken");
//            nonAdminToken = props.getProperty("nonAdminToken");
//            expiredToken = props.getProperty("expiredToken");
//            invalidToken = props.getProperty("invalidToken");
//
//            if (adminToken == null || nonAdminToken == null || expiredToken == null || invalidToken == null)
//                throw new IllegalArgumentException(
//                        "Properties file must exist and must contain "
//                                + "all tokens for tests to work.");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    void testReturnsUserPrincipleObjectWithAdminWhenGivenAdminToken() {

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
    void testReturnsUserPrincipleObjectWithNonAdminWhenGivenNonAdminToken() {

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
    void testReturnsNothingWhenGivenExpiredToken() {

        OAuth2Authenticator oAuth2Authenticator = new OAuth2Authenticator();
        Optional<User> returnedOptional = oAuth2Authenticator.authenticate(expiredToken);

        assertFalse(returnedOptional.isPresent());
    }

    @Test
    void testReturnsNothingWhenGivenInvalidToken() {

        OAuth2Authenticator oAuth2Authenticator = new OAuth2Authenticator();
        Optional<User> returnedOptional = oAuth2Authenticator.authenticate(invalidToken);

        assertFalse(returnedOptional.isPresent());
    }
}
