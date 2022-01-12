package com.kainos.ea.controller;
//models import
import com.kainos.ea.model.JobRole;
import com.kainos.ea.model.EditJobRole;
import com.kainos.ea.model.AddJobRole;
import com.kainos.ea.model.JobSpecModel;
import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.RoleMatrixResponseModel;
import com.kainos.ea.model.Competency;
import com.kainos.ea.model.UserRequestModel;
import com.kainos.ea.model.JobTraining;
import com.kainos.ea.model.JobFamilyModel;
import com.kainos.ea.model.CapabilityLead;
import com.kainos.ea.model.CapabilityRequest;
//services imported
import com.kainos.ea.service.BandLevelService;
import com.kainos.ea.service.JobFamiliesService;
import com.kainos.ea.service.CapabiltyService;
import com.kainos.ea.service.CompetencyService;
import com.kainos.ea.service.JobRolesService;
import com.kainos.ea.service.LoginService;
//swagger imports
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiKeyAuthDefinition;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.eclipse.jetty.http.HttpStatus;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
//authenticator
import javax.ws.rs.core.HttpHeaders;
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
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public class Employee {

    public JobRolesService jobRolesService = new JobRolesService();
    public JobFamiliesService jobFamiliesService = new JobFamiliesService();
    public CapabiltyService capabiltyService = new CapabiltyService();
    public CompetencyService competencyService = new CompetencyService();
    public BandLevelService bandLevelService = new BandLevelService();

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
            System.out.println("SQL EXCEPTION while getting job roles"
                    + ex.getMessage());
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
    public Response getJobRole(@PathParam("jobRoleID") int jobRoleID){
        try {
            EditJobRole jobRole = jobRolesService.getJobRole(jobRoleID);
            return Response.ok(jobRole).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting the job role "
                    + ex.getMessage());
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

        if(x != 0) {
            return Response.status(HttpStatus.CREATED_201).build();
        }
        else {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }


    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @POST
    @Path("/editJobRole/{jobRoleID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editJobRole(@PathParam("jobRoleID") int jobRoleID, AddJobRole editJobRole) {
        JobRolesService jobRolesService = new JobRolesService();
        int x = jobRolesService.editJobRole(editJobRole, jobRoleID);

        if(x == 1) {
            return Response.status(HttpStatus.CREATED_201).build();
        }
        else {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }


    @POST
    @Path("/deleteJobRole/{jobRoleID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteJobRole(@PathParam("jobRoleID") int jobRoleID) {
        try {
            JobRolesService jobRolesService = new JobRolesService();
            int x = jobRolesService.deleteJobRole(jobRoleID);

            if (x == 1) {
                return Response.status(HttpStatus.CREATED_201).build();
            }
            else {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
        }catch(SQLException e)
        {
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
    @Path("/getJobBandLevels")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobBandLevels(){
        try{
            List<String> jobBandLevels = bandLevelService.getJobBandLevels();
            return Response.ok(jobBandLevels).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job band levels" + ex.getMessage());
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
    @Path("/getJobFamilyNames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobFamilyNames(){
        try{
            List<String> jobFamilyNames = jobFamiliesService.getJobFamilyNames();
            return Response.ok(jobFamilyNames).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job capabilities"
                    + ex.getMessage());
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
    @Path("/getJobCapabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobCapabilities(){
        try{
            List<String> jobCapabilities = capabiltyService.getJobCapabilities();
            return Response.ok(jobCapabilities).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job capabilities"
                    + ex.getMessage());
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
    @Path("/getJobSpec/{jobRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpec(@PathParam("jobRoleID") int jobRoleID){
        try{
            JobSpecModel jobSpecModel= jobRolesService.getJobSpec(jobRoleID);
            return Response.ok(jobSpecModel).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles"
                    + ex.getMessage());
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
    @Path("/getJobCompetency/{jobRoleID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobComp(@PathParam("jobRoleID") int jobRoleID){
        try{
            Competency competency = competencyService.getComp(jobRoleID);
            return Response.ok(competency).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles"
                    + ex.getMessage());
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
    public Response getRoleMatrix(){
        try{
            RoleMatrixResponseModel roleMatrix = jobRolesService.getRoleMatrix();
            return Response.ok(roleMatrix).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: "
                    + ex.getMessage());
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
    @Path("/getJobTraining/{bandLevel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobTraining(@PathParam("bandLevel") String bandLevel){
        try{
            List<JobTraining> jobTraining= jobRolesService.getJobTraining(bandLevel);
            return Response.ok(jobTraining).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job roles"
                    + ex.getMessage());
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
            System.out.println("SQL Exception during login: "
                    + sqlException.getMessage());
        }catch (Exception ex){
            System.out.println("Exception while hashing: "
                    + ex.getMessage());
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
        }catch (SQLException sqlException){
            System.out.println("SQL Exception during login: "
                    + sqlException.getMessage());
        }catch (Exception ex){
            System.out.println("Exception while hashing: "
                    + ex.getMessage());
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
    public Response getJobFamilies(){
        try{
            List<JobFamilyModel> jobFamilyModels = jobFamiliesService.getJobFamilies();
            return Response.ok(jobFamilyModels).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting job family models: "
                    + ex.getMessage());
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
    @Path("/getAllCapabilityLead")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilityLead(){
        System.out.println("Authenticated");
        try{
            List<CapabilityLead> capabilityLead = capabiltyService.getAllCapabilityLeads();
            return Response.ok(capabilityLead).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: "
                    + ex.getMessage());
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
    @Path("/getCapabilityLead/{leadID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilityLead(@PathParam("leadID")int leadID){
        try{
            CapabilityLead capabilityLead = capabiltyService.getCapabilitylead(leadID);
            return Response.ok(capabilityLead).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: "
                    + ex.getMessage());
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
    @Path("/createCapability")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCapability(CapabilityRequest capabilityRequest) {
        try {
            int var = capabiltyService.createCapability(capabilityRequest);
            if (var == 0) {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
            return Response.status(HttpStatus.CREATED_201).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION "
                    + ex.getMessage());
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
    @Path("/getAllCapabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilities(){
        try{
            List<Capabilities> capabilities = capabiltyService.getAllCapabilites();
            return Response.ok(capabilities).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: "
                    + ex.getMessage());
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
    @Path("/updateCapability")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCapability(Capabilities capabilities) {
        try {
            System.out.println(capabilities);
            int var = capabiltyService.updateCapability(capabilities);
            if (var == 0) {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
            return Response.status(HttpStatus.CREATED_201).build();
        }catch (SQLException ex) {
            System.out.println("SQL EXECEPTION "
                    + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();

    }

}