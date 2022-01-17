package com.kainos.ea.controller;

import com.kainos.ea.model.JobTraining;
import com.kainos.ea.service.TrainingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import org.eclipse.jetty.http.HttpStatus;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Path("training")
@Produces(MediaType.APPLICATION_JSON)
public class Training {

    public TrainingService trainingService = new TrainingService();

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @GET
    @Path("/getJobTraining/{bandLevel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobTraining(@PathParam("bandLevel") String bandLevel) {
        try {
            List<JobTraining> jobTraining = trainingService.getJobTraining(bandLevel);
            return Response.ok(jobTraining).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
}
