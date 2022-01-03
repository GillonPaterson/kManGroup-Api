package com.kainos.ea.controller;

import com.kainos.ea.model.*;
import com.kainos.ea.service.JobFamiliesService;
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
    public JobFamiliesService jobFamiliesService = new JobFamiliesService();

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
    @Path("/getJobTrainingDP/{bandLevel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobTrainingDP(@PathParam("bandLevel") String bandLevel){
        try{
            List<JobTraining> jobTraining= jobRolesService.getJobTrainingDP(bandLevel);
            return Response.ok(jobTraining).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @GET
    @Path("/getJobTrainingPS/{bandLevel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobTrainingPS(@PathParam("bandLevel") String bandLevel){
        try{
            List<JobTraining> jobTraining= jobRolesService.getJobTrainingPS(bandLevel);
            return Response.ok(jobTraining).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @GET
    @Path("/getJobTrainingTS/{bandLevel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobTrainingTS(@PathParam("bandLevel") String bandLevel){
        try{
            List<JobTraining> jobTraining= jobRolesService.getJobTrainingTS(bandLevel);
            return Response.ok(jobTraining).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @GET
    @Path("/getJobFamilies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilies(){
        try{
            JobFamilyRequestModel jobFamilyRequestModel = jobFamiliesService.getJobFamilies();
            return Response.ok(jobFamilyRequestModel).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job family models: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
}
