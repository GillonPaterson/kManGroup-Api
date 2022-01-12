package com.kainos.ea.util;

import io.dropwizard.auth.Authorizer;

public class CoreAuthorizer implements Authorizer<User> {
    @Override
    public boolean authorize(User user, String role) {
        System.out.println("Authorisation Happening");
        return user.getName() != null && user.getRoles().contains(role);
    }
}
