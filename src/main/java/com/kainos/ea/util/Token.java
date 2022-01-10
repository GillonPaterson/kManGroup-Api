package com.kainos.ea.util;

import com.kainos.ea.model.TokenSubject;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class Token {
    private static final String SECRET_KEY = "2w0lavt3CFAAqAY1z4q+LpZfCNW5gLH+udmMfi/Tl6g=";

    public String createToken(TokenSubject subject, long lifeSpan){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date exp = new Date(nowMillis+lifeSpan);

        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
        String jws = Jwts.builder()
                .setIssuedAt(now)
                .claim("username", subject.getUsername())
                .claim("isAdmin", subject.isAdmin)
                .signWith(key)
                .setExpiration(exp)
                .compact();

        return jws;
    }

    public Jws<Claims> decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(Base64.getDecoder().decode(SECRET_KEY))
                    .build()
                    .parseClaimsJws(jwt);
            return jws;
        }
        catch (JwtException ex) {
            System.out.println("Error verifying: " + ex.getMessage());
            return null;
        }
    }
}
