/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.arvuti;

import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysListRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysListResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.OpsysType;
import java.math.BigInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author dell
 */
@Path("opsystems")
public class OpsystemsResource {

    @Context
    private UriInfo context;

    public OpsystemsResource() {
    }

    /**
     *
     * @param token
     * @return
     */
    @GET
    @Produces("application/json")
    public GetOpsysListResponse getOpsysList (@QueryParam("token") String token) {
        ArvutiWebService ws = new ArvutiWebService();
        GetOpsysListRequest request = new GetOpsysListRequest();
        request.setToken(token);
        return ws.getOpsysList(request);
    }

    @GET
    @Path("{id : \\d+}") //supports digits only
    @Produces("application/json")
    public OpsysType getOpsys(@PathParam("id") String id,
            @QueryParam("token") String token) {
        ArvutiWebService ws = new ArvutiWebService();
        GetOpsysRequest request = new GetOpsysRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getOpsys(request);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public OpsysType addOpsys(OpsysType content,
                                @QueryParam("token") String token) {
        ArvutiWebService ws = new ArvutiWebService();
        AddOpsysRequest request = new AddOpsysRequest();
        request.setCode(content.getCode());
        request.setName(content.getName());
        request.setToken(token);
        return ws.addOpsys(request);
    }
}
