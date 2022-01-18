package util;

import com.kainos.ea.model.TokenSubject;
import com.kainos.ea.util.TokenHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TokenHandlerTest {
    @Test
    void testTokenCreatorCreatesValidTokenWithClaimsByDecodingIt() {
        TokenSubject tokenSubject = new TokenSubject("test", false);

        TokenHandler tokenHandler = new TokenHandler();

        String token = tokenHandler.createToken(tokenSubject, 10000);
        Jws<Claims> claims = tokenHandler.decodeJWT(token);

        //If fails cause claims is null, means not decoding it, error, not verified or expired
        assertFalse((boolean) claims.getBody().get("isAdmin"));
        assertEquals("test", claims.getBody().get("username"));
    }
}
