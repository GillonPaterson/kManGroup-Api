package com.kainos.ea.controller;

import com.kainos.ea.model.*;
import com.kainos.ea.service.JobRolesService;
import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
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
@Path("job-roles")
@Produces(MediaType.APPLICATION_JSON)
public class JobRoles {

    public JobRolesService jobRolesService = new JobRolesService();

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @GET
    @Path("/getJobRoles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() {
        try {
            List<JobRole> jobRoles = jobRolesService.getJobRoles();
            return Response.ok(jobRoles).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @GET
    @Path("/getJobRole/{jobRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRole(@PathParam("jobRoleID") int jobRoleID) {
        try {
            EditJobRole jobRole = jobRolesService.getJobRole(jobRoleID);
            return Response.ok(jobRole).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting the job role " + ex.getMessage());
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
    @Path("/addJobRole")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addJobRole(AddJobRole addJobRoles) {
        JobRolesService jobRolesService = new JobRolesService();
        int x = jobRolesService.addJobRole(addJobRoles);

        if (x != 0) {
            return Response.status(HttpStatus.CREATED_201).build();
        } else {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();

        }
    }


    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @RolesAllowed("Admin")
    @PUT
    @Path("/editJobRole/{jobRoleID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editJobRole(@PathParam("jobRoleID") int jobRoleID, AddJobRole editJobRole) {
        JobRolesService jobRolesService = new JobRolesService();
        int x = jobRolesService.editJobRole(editJobRole, jobRoleID);
        if (x == 1) {
            return Response.status(HttpStatus.CREATED_201).build();

        } else {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @RolesAllowed("Admin")
    @DELETE
    @Path("/deleteJobRole/{jobRoleID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRole(@PathParam("jobRoleID") int jobRoleID) {
        try {
            JobRolesService jobRolesService = new JobRolesService();
            int x = jobRolesService.deleteJobRole(jobRoleID);

            if (x == 1) {
                return Response.status(HttpStatus.CREATED_201).build();
            } else {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
        } catch (SQLException e) {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @GET
    @Path("/getJobSpec/{jobRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpec(@PathParam("jobRoleID") int jobRoleID) {
        try {
            JobSpecModel jobSpecModel = jobRolesService.getJobSpec(jobRoleID);
            return Response.ok(jobSpecModel).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
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
    @Path("/getRoleMatrix")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoleMatrix() {
        try {
            RoleMatrixResponseModel roleMatrix = jobRolesService.getRoleMatrix();
            return Response.ok(roleMatrix).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
}
