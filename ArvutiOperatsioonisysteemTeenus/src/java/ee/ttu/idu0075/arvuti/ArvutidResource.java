/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.arvuti;

import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysToArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysToArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiType;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutidWithParametersRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutidWithParametersResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsInArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsInArvutiResponse;
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
@Path("arvutid")
public class ArvutidResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ArvutidResource
     */
    public ArvutidResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddArvutiResponse addArvuti(ArvutiType arvuti, 
                                @QueryParam ("token") String token, @QueryParam ("requestID") String requestID) {
        ArvutiWebService service = new ArvutiWebService();
        AddArvutiRequest request = new AddArvutiRequest();
        request.setToken(token);
        request.setRequestID(requestID);
        request.setUserName(arvuti.getUserName());
        request.setQuantity(arvuti.getQuantity());
        return service.addArvuti(request);
    }
    
    @GET
    @Path("{id :  \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public GetArvutiResponse getArvuti(@QueryParam ("token") String token, 
            @QueryParam ("requestID") String requestID, @PathParam ("id") String id ) {
        ArvutiWebService service = new ArvutiWebService();
        GetArvutiRequest request = new GetArvutiRequest();
        request.setToken(token);
        request.setRequestID(requestID);
        request.setArvutiID(new BigInteger(id));
        return service.getArvuti(request);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetArvutidWithParametersResponse getArvutidWithParameters(@QueryParam ("token") String token, 
            @QueryParam ("requestID") String requestID, @QueryParam ("userName") String userName, @QueryParam ("quantity") String quantity ) {
        ArvutiWebService service = new ArvutiWebService();
        GetArvutidWithParametersRequest request = new GetArvutidWithParametersRequest();
        request.setToken(token);
        request.setRequestID(requestID);
        if (userName != null ) request.setUserName(userName);
        if (quantity != null ) request.setQuantity(new BigInteger(quantity));
        return service.getArvutidWithParameters(request);
    }
    
    @POST
    @Path("{id : \\d+}/photos") 
    @Consumes("application/json")
    @Produces("application/json")
    public AddOpsysToArvutiResponse addOpsysToArvuti(AddOpsysToArvutiRequest content, 
                                @QueryParam("token") String token, @QueryParam ("requestID") String requestID,
                                @PathParam("id") String id) {
        ArvutiWebService service = new ArvutiWebService();
        AddOpsysToArvutiRequest request = new AddOpsysToArvutiRequest();
        request.setToken(token);
        request.setRequestID(requestID);
        System.out.println("opsystems: " + content.getOpsysID().toString());
        System.out.println("computers: " + content.getArvutiID().toString());
        for (BigInteger oid : content.getOpsysID()) {
            request.getOpsysID().add(oid);
        }
        for (BigInteger aid : content.getArvutiID()) {
            request.getArvutiID().add(aid);
        }
        return service.addOpsysToArvuti(request);    
    }
    
    @GET
    @Path("{id : \\d+}/opsystems") //support digit only
    @Produces("application/json")
    public GetOpsystemsInArvutiResponse getOpsystemsInArvuti( 
                                @QueryParam("token") String token, @QueryParam ("requestID") String requestID, @PathParam("id") String id) {
        ArvutiWebService service = new ArvutiWebService();
        GetOpsystemsInArvutiRequest request = new GetOpsystemsInArvutiRequest();
        request.setToken(token);
        request.setRequestID(requestID);
        request.setArvutiID(new BigInteger(id));
        return service.getOpsystemsInArvuti(request); 
    }
    
}
