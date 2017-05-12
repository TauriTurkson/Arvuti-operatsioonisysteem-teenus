/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.arvuti;

import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiOpsysListType;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiOpsysType;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiType;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiListRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiListResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiOpsysListRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiRequest;
import java.math.BigInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author dell
 */
@Path("arvutid")
@RequestScoped
public class ArvutidResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ArvutidResource
     */
    public ArvutidResource() {
    }

    /**
     * Retrieves representation of an instance of arvuti.ArvutidResource
     * @param token
     * @param hasRelatedOpsystems
     */
    
    @GET
    @Produces("application/json")
    public GetArvutiListResponse getArvutiList (@QueryParam("token") String token, 
            @QueryParam("opsystems") String hasRelatedOpsystems) {
        ArvutiWebService ws = new ArvutiWebService();
        GetArvutiListRequest request = new GetArvutiListRequest();
        request.setToken(token);
        request.setHasRelatedOpsystems(hasRelatedOpsystems);
        return ws.getArvutiList(request);
    }
    
    @GET
    @Path("{id : \\d+}") //support digit only
    @Produces("application/json")
    public ArvutiType getArvuti(@PathParam("id") String id,
            @QueryParam("token") String token) {
        ArvutiWebService ws = new ArvutiWebService();
        GetArvutiRequest request = new GetArvutiRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getArvuti(request);
    }
    
    /**
     *
     * @param content
     * @param token
     * @return
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ArvutiType addArvuti(ArvutiType content,
                                @QueryParam("token") String token) {
        ArvutiWebService ws = new ArvutiWebService();
        AddArvutiRequest request = new AddArvutiRequest();
        request.setToken(token);
        request.setArvutiNo(content.getArvutiNo());
        request.setUserName(content.getUserName());
        return ws.addArvuti(request);
    }
    
    @GET
    @Path("{id : \\d+}/opsystems") //support digit only
    @Produces("application/json")
    public ArvutiOpsysListType getArvutiOpsysList (@PathParam("id") String id,
            @QueryParam("token") String token,
            @QueryParam("name") String name,
            @QueryParam("requestID") String requestID) {
        ArvutiWebService ws = new ArvutiWebService();
        GetArvutiOpsysListRequest request = new GetArvutiOpsysListRequest();
        request.setArvutiId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        request.setName(name);
        request.setRequestID(requestID);
        return ws.getArvutiOpsysList(request);
    }
    
    /**
     *
     * @param content
     * @param token
     * @param id
     * @return
     */
    @POST
    @Path("{id : \\d+}/opsystems") //support digit only
    @Consumes("application/json")
    @Produces("application/json")
    public ArvutiOpsysType addArvutiOpsys(AddArvutiOpsysRequest content,
                                @QueryParam("token") String token,
                                @PathParam("id") String id) {
        ArvutiWebService ws = new ArvutiWebService();
        AddArvutiOpsysRequest request = new AddArvutiOpsysRequest();
        request.setToken(token);
        request.setArvutiId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setOpsysId(content.getOpsysId());
        request.setQuantity(content.getQuantity());
        request.setVersion(content.getVersion());
        request.setRequestID(content.getRequestID());
        return ws.addArvutiOpsys(request);
    }
}
