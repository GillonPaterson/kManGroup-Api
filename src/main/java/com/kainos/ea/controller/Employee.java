package com.kainos.ea.controller;

import com.kainos.ea.model.*;

import com.kainos.ea.service.AdminLoginService;

import com.kainos.ea.service.JobRolesService;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
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

    @GET
    @Path("/getRoleMatrix")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoleMatrix(){
        try{
            RoleMatrixResponseModel roleMatrix = jobRolesService.getRoleMatrix();
            return Response.ok(roleMatrix).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @GET
    @Path("/getJobTraining/{bandLevel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobTraining(@PathParam("bandLevel") String bandLevel){
        try{
            List<JobTraining> jobTraining= jobRolesService.getJobTraining(bandLevel);
            return Response.ok(jobTraining).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @POST
    @Path("/checkDetails")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkDetails(Details details) {
        AdminLoginService loginService = new AdminLoginService();
        return Response.status(HttpStatus.OK_200).entity(loginService.checkDetails(details)).build();
    }
}
