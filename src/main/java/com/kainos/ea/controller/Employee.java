package com.kainos.ea.controller;

import com.kainos.ea.model.*;
import com.kainos.ea.service.CapabiltyService;
import com.kainos.ea.service.JobFamiliesService;

import com.kainos.ea.service.JobRolesService;
import com.kainos.ea.service.LoginService;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
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
    public CapabiltyService capabiltyService = new CapabiltyService();


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


    @POST
    @Path("/addJobRole")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addJobRole(AddJobRole addJobRoles) {
        JobRolesService jobRolesService = new JobRolesService();
        int x = jobRolesService.addJobRole(addJobRoles);

        if(x != 0)
            return Response.status(HttpStatus.CREATED_201).build();
        else
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @GET
    @Path("/getJobBandLevels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobBandLevels(){
        try{
            List<String> jobBandLevels = jobRolesService.getJobBandLevels();
            return Response.ok(jobBandLevels).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job band levels" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }


    @GET
    @Path("/getJobFamilyNames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilyNames(){
        try{
            List<String> jobFamilyNames = jobFamiliesService.getJobFamilyNames();
            return Response.ok(jobFamilyNames).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job capabilities" + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }


    @GET
    @Path("/getJobCapabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobCapabilities(){
        try{
            List<String> jobCapabilities = jobRolesService.getJobCapabilities();
            return Response.ok(jobCapabilities).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job capabilities" + ex.getMessage());
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
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(UserRequestModel loginInfo) {
        LoginService loginService = new LoginService();
        try {
            String jwt = loginService.checkDetails(loginInfo);
            return Response
                    .status(HttpStatus.OK_200)
                    .header(HttpHeaders.AUTHORIZATION,"Bearer " + jwt)
                    .build();
        }catch (SQLException sqlException){
            System.out.println("SQL Exception during login: " + sqlException.getMessage());
        }catch (Exception ex){
            System.out.println("Exception while hashing: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @POST
    @Path("/createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserRequestModel userInfo) {
        LoginService loginService = new LoginService();
        try {
            loginService.registerUser(userInfo);
            return Response.status(HttpStatus.OK_200).build();
        }catch (SQLException sqlException){
            System.out.println("SQL Exception during login: " + sqlException.getMessage());
        }catch (Exception ex){
            System.out.println("Exception while hashing: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @GET
    @Path("/getJobFamilies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilies(){
        try{
            List<JobFamilyModel> jobFamilyModels = jobFamiliesService.getJobFamilies();
            return Response.ok(jobFamilyModels).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job family models: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
  
    @GET
    @Path("/getAllCapabilityLead")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilityLead(){
        try{
            List<CapabilityLead> capabilityLead = capabiltyService.getAllCapabilityLeads();
            return Response.ok(capabilityLead).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }


    @GET
    @Path("/getCapabilityLead/{leadID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilityLead(@PathParam("leadID")int leadID){
        try{
            CapabilityLead capabilityLead = capabiltyService.getCapabilitylead(leadID);
            return Response.ok(capabilityLead).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @POST
    @Path("/createCapability")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCapability(CapabilityRequest capabilityRequest) {
        try {
            System.out.println(capabilityRequest.getCapabilityName());
            capabiltyService.createCapability(capabilityRequest);
            return Response.status(HttpStatus.CREATED_201).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXECEPTION "+ ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();

    }

}
