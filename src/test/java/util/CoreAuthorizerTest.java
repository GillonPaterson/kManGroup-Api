package util;

import com.google.common.collect.ImmutableSet;
import com.kainos.ea.util.CoreAuthorizer;
import com.kainos.ea.util.User;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CoreAuthorizerTest {
    @Test
    void testReturnTrueForRoleThatEqual(){
        CoreAuthorizer coreAuthorizer = new CoreAuthorizer();
        Set<String> roles = new HashSet<>();
        roles.add("Admin");
        User user = new User("Test", roles);
        boolean result = coreAuthorizer.authorize(user, "Admin");

        assertTrue(result);
    }

    @Test
    void testReturnFalseForRoleThatAreNotEqual(){
        CoreAuthorizer coreAuthorizer = new CoreAuthorizer();
        Set<String> roles = new HashSet<>();
        User user = new User("Test", roles);
        boolean result = coreAuthorizer.authorize(user, "Admin");

        assertFalse(result);
    }
}
