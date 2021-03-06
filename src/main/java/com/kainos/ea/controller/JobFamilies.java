package com.kainos.ea.controller;

import com.kainos.ea.model.JobFamilyModel;
import com.kainos.ea.service.JobFamiliesService;
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
@Path("job-families")
@Produces(MediaType.APPLICATION_JSON)
public class JobFamilies {

    public JobFamiliesService jobFamiliesService = new JobFamiliesService();

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @GET
    @Path("/getJobFamilyNames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilyNames() {
        try {
            List<String> jobFamilyNames = jobFamiliesService.getJobFamilyNames();
            return Response.ok(jobFamilyNames).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job capabilities" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @GET
    @Path("/getJobFamilies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilies() {
        try {
            List<JobFamilyModel> jobFamilyModels = jobFamiliesService.getJobFamilies();
            return Response.ok(jobFamilyModels).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job family models: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
}
