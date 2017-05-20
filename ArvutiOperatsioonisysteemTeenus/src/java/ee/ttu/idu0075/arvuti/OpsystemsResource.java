/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.arvuti;

import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsWithParametersRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsWithParametersResponse;
import ee.ttu.idu0075._2015.ws.arvuti.OpsysType;
import java.math.BigInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author dell
 */
@Path("opsystems")
public class OpsystemsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OpsystemsResource
     */
    public OpsystemsResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddOpsysResponse addOpsys(OpsysType opsys, 
                                @QueryParam ("token") String token,
                                @QueryParam ("requestID") String requestID) {
        ArvutiWebService service = new ArvutiWebService();
        AddOpsysRequest request = new AddOpsysRequest();
        request.setToken(token);
        request.setRequestID(requestID);
        request.setDeveloper(opsys.getDeveloper());
        request.setName(opsys.getName());
        request.setRelease(opsys.getRelease());
        return service.addOpsys(request);
    }
    
    @GET
    @Path("{id :  \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetOpsysResponse getOpsys(@QueryParam ("token") String token, 
            @QueryParam ("requestID") String requestID, @PathParam ("id") String id ) {
        ArvutiWebService service = new ArvutiWebService();
        GetOpsysRequest request = new GetOpsysRequest();
        request.setToken(token);
        request.setRequestID(requestID);
        request.setOpsysID(new BigInteger(id));
        return service.getOpsys(request);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetOpsystemsWithParametersResponse getOpsystemsWithParameters(@QueryParam ("token") String token, 
            @QueryParam ("requestID") String requestID, @QueryParam ("developer") String developer, @QueryParam ("name") String name ) {
        ArvutiWebService service = new ArvutiWebService();
        GetOpsystemsWithParametersRequest request = new GetOpsystemsWithParametersRequest();
        request.setToken(token);
        request.setRequestID(requestID);
        if (developer!= null ) request.setDeveloper(developer);
        if (name != null) request.setName(name);     
        return service.getOpsystemsWithParameters(request);
    }
    
}
