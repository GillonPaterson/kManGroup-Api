package com.kainos.ea.controller;

import com.kainos.ea.model.Capabilities;
import com.kainos.ea.model.CapabilityLead;
import com.kainos.ea.model.CapabilityName;
import com.kainos.ea.model.CapabilityRequest;
import com.kainos.ea.service.CapabiltyService;
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
@Path("capability")
@Produces(MediaType.APPLICATION_JSON)
public class Capability {

    public CapabiltyService capabiltyService = new CapabiltyService();

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @PermitAll
    @GET
    @Path("/getJobCapabilities")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobCapabilities() {
        try {
            List<String> jobCapabilities = capabiltyService.getJobCapabilities();
            return Response.ok(jobCapabilities).build();
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
    @Path("/getAllCapabilityLead")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCapabilityLead() {
        System.out.println("Authenticated");
        try {
            List<CapabilityLead> capabilityLead = capabiltyService.getAllCapabilityLeads();
            return Response.ok(capabilityLead).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: " + ex.getMessage());
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
    public Response getAllCapabilityLead(@PathParam("leadID") int leadID) {
        try {
            CapabilityLead capabilityLead = capabiltyService.getCapabilitylead(leadID);
            return Response.ok(capabilityLead).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: " + ex.getMessage());
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
        } catch (SQLException ex) {
            System.out.println("SQL EXECEPTION " + ex.getMessage());
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
    public Response getAllCapabilities() {
        try {
            List<Capabilities> capabilities = capabiltyService.getAllCapabilites();
            return Response.ok(capabilities).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION while getting role matrix: " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }

    @ApiOperation(authorizations = @Authorization("custom"),
            value = "Requires Authentication. Returns dashboard",
            notes = "Requires Authentication. Returns dashboard",
            response = Response.class
    )
    @RolesAllowed("Admin")
    @PUT
    @Path("/updateCapability/{capabilityID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCapability(@PathParam("capabilityID") int capabilityID, CapabilityName capabilityName) {
        try {

            System.out.println(capabilityName.getCapabilityname());
            Capabilities capabilities = new Capabilities(capabilityID,capabilityName.getCapabilityname());

            boolean var = capabiltyService.updateCapability(capabilities);
            if (!var) {
                return Response.status(HttpStatus.BAD_REQUEST_400).build();
            }
            return Response.status(HttpStatus.CREATED_201).build();
        } catch (SQLException ex) {
            System.out.println("SQL EXECEPTION " + ex.getMessage());
        }
        return Response.status(HttpStatus.BAD_REQUEST_400).build();
    }
}
