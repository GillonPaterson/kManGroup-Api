package util;

import com.kainos.ea.util.Hasher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HasherTest {
    @Test
    void testHasherHashPasswordHashesCorrectly() throws Exception{
        Hasher hasher = new Hasher();

        String password = "password";
        String salt = "salt";

        String returnedHash = hasher.hashPassword(password, salt);
        String expectedHash = "+3Hwrj2jO4etwTOK92cyKg==";
        assertEquals(expectedHash, returnedHash);
    }
}
