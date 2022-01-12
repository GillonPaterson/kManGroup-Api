package com.kainos.ea.util;

import com.google.common.collect.ImmutableSet;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OAuth2Authenticator implements Authenticator<String, User> {
    private static final String SECRET_KEY = "2w0lavt3CFAAqAY1z4q+LpZfCNW5gLH+udmMfi/Tl6g=";

    @Override
    public Optional<User> authenticate(String token) {
        System.out.println("Authentication Happening");
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(Base64.getDecoder().decode(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token);
            Set<String> roles = new HashSet<>();
            if(jws.getBody().get("isAdmin").equals(true)){
                roles.add("Admin");
            }
            return Optional.of(new User(jws.getBody().get("username").toString(), roles));
        }catch (JwtException ex){
            return Optional.empty();
        }
    }
}
