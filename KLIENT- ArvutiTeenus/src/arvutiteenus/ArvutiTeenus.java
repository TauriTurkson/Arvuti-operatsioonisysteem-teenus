/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvutiteenus;

import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiType;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiListRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiListResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.OpsysType;
import java.math.BigInteger;
/**
 *
 * @author dell
 */
public class ArvutiTeenus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            AddArvutiRequest request =  new AddArvutiRequest();
            request.setToken("salajane");
            request.setArvutiNo("IDU123");
            request.setUserName("dell");
            ArvutiType response = addArvuti(request);
            System.out.println("Lisatud arvuti " + response.getArvuti().getArvutiId() 
                + " numbriga: " + response.getArvuti().getArvutiNo()
                + " kasutaja: " + response.getArvuti().getUserName());
            
            AddArvutiRequest request1 =  new AddArvutiRequest();
            request1.setToken("salajane");
            request1.setArvutiNo("IDU124");
            request1.setUserName("dell");
            ArvutiType response1 = addArvuti(request1);
            System.out.println("Lisatud arvuti " + response1.getArvuti().getArvutiId() 
                + " numbriga: " + response1.getArvuti().getArvutiNo()
                + " kasutaja: " + response1.getArvuti().getUserName());
            
            GetArvutiRequest getArvuti = new GetArvutiRequest();
            getArvuti.setToken("salajane");
            getArvuti.setId(BigInteger.ONE);
            ArvutiType response2 = getArvuti(getArvuti);
            System.out.println("Arvuti: " + response2.getArvuti().getArvutiId() 
                    + " with title: " + response2.getArvuti().getArvutiNo());
            
            GetOpsysRequest getOpSys = new GetOpsysRequest();
            getOpSys.setToken("salajane");
            getOpSys.setId(BigInteger.ONE);
            OpsysType opsystems = getOpsys(getOpSys);
            System.out.println("Leiti " + opsystems.getOpsys().id() + " 'ga operatsioonisüsteem.");
    }

    private static ArvutiType addArvuti(ee.ttu.idu0075._2015.ws.arvuti.AddArvutiRequest parameter) {
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiService service = new ee.ttu.idu0075._2015.ws.arvuti.ArvutiService();
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiPortType port = service.getArvutiPort();
        return port.addArvuti(parameter);
    }

    private static ArvutiType getArvuti(ee.ttu.idu0075._2015.ws.arvuti.GetArvutiRequest parameter) {
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiService service = new ee.ttu.idu0075._2015.ws.arvuti.ArvutiService();
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiPortType port = service.getArvutiPort();
        return port.getArvuti(parameter);
    }

    private static OpsysType getOpsys(ee.ttu.idu0075._2015.ws.arvuti.GetOpsysRequest parameter) {
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiService service = new ee.ttu.idu0075._2015.ws.arvuti.ArvutiService();
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiPortType port = service.getArvutiPort();
        return port.getOpsys(parameter);
    }

}
