package com.kainos.ea.controller;

import com.kainos.ea.model.UserRequestModel;
import com.kainos.ea.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import org.eclipse.jetty.http.HttpStatus;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "custom",
                        name = "authorization",
                        in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER,
                        description = "Bearer Authentication")}))

@Api
@Path("login")
@Produces(MediaType.APPLICATION_JSON)
public class Login {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserRequestModel loginInfo) {
        LoginService loginService = new LoginService();
        try {
            String jwt = loginService.checkDetails(loginInfo);
            return Response
                    .status(HttpStatus.OK_200)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                    .build();
        } catch (SQLException sqlException) {
            System.out.println("SQL Exception during login: " + sqlException.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception while hashing: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @RolesAllowed("Admin")
    @POST
    @Path("/createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserRequestModel userInfo) {
        LoginService loginService = new LoginService();
        try {
            loginService.registerUser(userInfo);
            return Response.status(HttpStatus.OK_200).build();
        } catch (SQLException sqlException) {
            System.out.println("SQL Exception during login: " + sqlException.getMessage());
        } catch (Exception ex) {
            System.out.println("Exception while hashing: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
}
