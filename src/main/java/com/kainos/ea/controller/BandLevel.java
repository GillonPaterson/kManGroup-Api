package com.kainos.ea.controller;

import com.kainos.ea.model.CreateBandLevelRequestModel;
import com.kainos.ea.service.BandLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import org.eclipse.jetty.http.HttpStatus;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@SwaggerDefinition(securityDefinition = @SecurityDefinition(
        apiKeyAuthDefinitions = {
                @ApiKeyAuthDefinition(key = "custom",
                        name = "authorization",
                        in = ApiKeyAuthDefinition.ApiKeyLocation.HEADER,
                        description = "Bearer Authentication")}))

@Api
@Path("band-level")
@Produces(MediaType.APPLICATION_JSON)
public class BandLevel {
    public BandLevelService bandLevelService = new BandLevelService();

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @GET
    @Path("/getJobBandLevels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobBandLevels() {
        try {
            List<String> jobBandLevels = bandLevelService.getJobBandLevels();
            return Response.ok(jobBandLevels).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job band levels" + ex.getMessage());
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
    @Path("/createBandLevel")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBandLevel(CreateBandLevelRequestModel createBandLevelRequestModel) {
        System.out.println(createBandLevelRequestModel);
        try {
            bandLevelService.createBandLevel(createBandLevelRequestModel);
            return Response.status(HttpStatus.CREATED_201).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXECEPTION " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
}
