package com.kainos.ea.controller;

import com.kainos.ea.model.Competency;
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.JobSpecModel;
import com.kainos.ea.service.JobRolesService;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Api
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public class Employee {

    public JobRolesService jobRolesService = new JobRolesService();

    @GET
    @Path("/getJobRoles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles(){
        try{
            List<JobRole> jobRoles= jobRolesService.getJobRoles();
            return Response.ok(jobRoles).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @GET
    @Path("/getJobSpec/{jobRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpec(@PathParam("jobRoleID") int jobRoleID){
        try{
            JobSpecModel jobSpecModel= jobRolesService.getJobSpec(jobRoleID);
            return Response.ok(jobSpecModel).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @GET
    @Path("/getJobCompetency/{jobRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobComp(@PathParam("jobRoleID") int jobRoleID){
        try{
            Competency competency = jobRolesService.getComp(jobRoleID);
            return Response.ok(competency).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
}
