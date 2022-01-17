package com.kainos.ea.controller;

import com.kainos.ea.service.BandLevelService;
import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
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
}
