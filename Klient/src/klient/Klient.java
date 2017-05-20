/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klient;

import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutidWithParametersRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutidWithParametersResponse;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author dell
 */
public class Klient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       AddArvutiRequest request =  new AddArvutiRequest();
        request.setToken("salajane");
        request.setRequestID("10");
        request.setUserName("Tauri");
        request.setQuantity(BigInteger.ZERO);
        AddArvutiResponse response = addArvuti(request);
        System.out.println("Added computer " + response.getArvuti().getArvutiID() 
                + " with username: " + response.getArvuti().getUserName()
                + " quantity: " + response.getArvuti().getQuantity());
        
        AddArvutiRequest request1 =  new AddArvutiRequest();
        request1.setToken("salajane");
        request1.setRequestID("11");
        request1.setUserName("Tauri2");
        request1.setQuantity(BigInteger.ZERO);
        AddArvutiResponse response1 = addArvuti(request1);
        System.out.println("Added computer " + response1.getArvuti().getArvutiID() 
                + " with username: " + response1.getArvuti().getUserName()
                + " quantity: " + response1.getArvuti().getQuantity());
        
        GetArvutiRequest getArvuti = new GetArvutiRequest();
        getArvuti.setToken("salajane");
        getArvuti.setRequestID("12");
        getArvuti.setArvutiID(BigInteger.ONE);
        GetArvutiResponse response2 = getArvuti(getArvuti);
        System.out.println("Got computer: " + response2.getArvuti().getArvutiID() 
                + " with username: " + response2.getArvuti().getUserName());
        
        GetArvutidWithParametersRequest getParam = new GetArvutidWithParametersRequest();
        getParam.setToken("salajane");
        getParam.setRequestID("13");
        getParam.setUserName("Tauri");
        GetArvutidWithParametersResponse arvutid = getArvutidWithParameters(getParam);
        //System.out.println("Got " + arvutid.getArvuti().getQuantity() + " computers with username Tauri");
    }

    private static AddArvutiResponse addArvuti(ee.ttu.idu0075._2015.ws.arvuti.AddArvutiRequest parameter) {
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiService service = new ee.ttu.idu0075._2015.ws.arvuti.ArvutiService();
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiPortType port = service.getArvutiPort();
        return port.addArvuti(parameter);
    }

    private static GetArvutiResponse getArvuti(ee.ttu.idu0075._2015.ws.arvuti.GetArvutiRequest parameter) {
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiService service = new ee.ttu.idu0075._2015.ws.arvuti.ArvutiService();
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiPortType port = service.getArvutiPort();
        return port.getArvuti(parameter);
    }

    private static GetArvutidWithParametersResponse getArvutidWithParameters(ee.ttu.idu0075._2015.ws.arvuti.GetArvutidWithParametersRequest parameter) {
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiService service = new ee.ttu.idu0075._2015.ws.arvuti.ArvutiService();
        ee.ttu.idu0075._2015.ws.arvuti.ArvutiPortType port = service.getArvutiPort();
        return port.getArvutidWithParameters(parameter);
    }
    
}
