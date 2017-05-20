/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.arvuti;

import com.sun.xml.ws.developer.SchemaValidation;
import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysResponse;
import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysToArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysToArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiType;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutidWithParametersRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutidWithParametersResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsInArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsInArvutiResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsWithParametersRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsWithParametersResponse;
import ee.ttu.idu0075._2015.ws.arvuti.OpsysType;
import ee.ttu.idu0075._2015.ws.arvuti.StatusType;
import java.math.BigInteger;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author dell
 */
@WebService(serviceName = "ArvutiService", portName = "ArvutiPort", endpointInterface = "ee.ttu.idu0075._2015.ws.arvuti.ArvutiPortType", targetNamespace = "http://www.ttu.ee/idu0075/2015/ws/arvuti", wsdlLocation = "WEB-INF/wsdl/ArvutiWebService/ArvutiService.wsdl")
@SchemaValidation
public class ArvutiWebService {
    
    Data dataService = new Data();
    
    public AddOpsysResponse addOpsys(AddOpsysRequest parameter) {
        String responseString = checkTokens(parameter.getToken(), parameter.getRequestID());
        String key = parameter.getToken() + "_" + parameter.getRequestID();
        AddOpsysResponse response = new AddOpsysResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setRequestID(parameter.getRequestID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            OpsysType opsys = new OpsysType();
            opsys.setDeveloper(parameter.getDeveloper());
            opsys.setName(parameter.getName());
            opsys.setRelease(parameter.getRelease());
            opsys.setOpsysID(dataService.getNewOpsysId());
            response.setOpsys(opsys);
            dataService.addOpsys(opsys);
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof AddOpsysResponse) {
                response = (AddOpsysResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }
    
    public AddArvutiResponse addArvuti(AddArvutiRequest parameter) {
        String responseString = checkTokens(parameter.getToken(), parameter.getRequestID());
        String key = parameter.getToken() + "_" + parameter.getRequestID();
        AddArvutiResponse response = new AddArvutiResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setRequestID(parameter.getRequestID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            ArvutiType arvuti = new ArvutiType();
            arvuti.setArvutiID(dataService.getNewArvutiId());
            arvuti.setUserName(parameter.getUserName());
            arvuti.setQuantity(parameter.getQuantity());
            response.setArvuti(arvuti);
            dataService.addArvuti(arvuti);
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof AddArvutiResponse) {
                response = (AddArvutiResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }
    
    public GetOpsysResponse getOpsys(GetOpsysRequest parameter) {
        String responseString = checkTokens(parameter.getToken(), parameter.getRequestID());
        String key = parameter.getToken() + "_" + parameter.getRequestID();
        GetOpsysResponse response = new GetOpsysResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setRequestID(parameter.getRequestID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            OpsysType opsys = dataService.getOpsysById(parameter.getOpsysID());
            if (opsys == null) {
                statusType.setMessage("No opsys with this ID exists!");
                response.setStatusMessage(statusType);
            } else {
                response.setOpsys(opsys);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetOpsysResponse) {
                response = (GetOpsysResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }
    
    public GetArvutiResponse getArvuti(GetArvutiRequest parameter) {
        String responseString = checkTokens(parameter.getToken(), parameter.getRequestID());
        String key = parameter.getToken() + "_" + parameter.getRequestID();
        GetArvutiResponse response = new GetArvutiResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setRequestID(parameter.getRequestID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            ArvutiType arvuti = dataService.getArvutiById(parameter.getArvutiID());
            if (arvuti == null) {
                statusType.setMessage("No arvuti with this ID exists!");
                response.setStatusMessage(statusType);
            } else {
                response.setArvuti(arvuti);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetArvutiResponse) {
                response = (GetArvutiResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }
    
    public GetOpsystemsWithParametersResponse getOpsystemsWithParameters(GetOpsystemsWithParametersRequest parameter) {
        String responseString = checkTokens(parameter.getToken(), parameter.getRequestID());
        String key = parameter.getToken() + "_" + parameter.getRequestID();
        GetOpsystemsWithParametersResponse response = new GetOpsystemsWithParametersResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setRequestID(parameter.getRequestID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            List<OpsysType> matches = dataService.getOpsystemsWithParameters(parameter);
            if (matches.isEmpty()) {
                statusType.setMessage("No opsystems were found");
                response.setStatusMessage(statusType);
                return response;
            } else {
                matches.forEach(response.getOpsys()::add);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetOpsystemsWithParametersResponse) {
                response = (GetOpsystemsWithParametersResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public GetArvutidWithParametersResponse getArvutidWithParameters(GetArvutidWithParametersRequest parameter) {
        String responseString = checkTokens(parameter.getToken(), parameter.getRequestID());
        String key = parameter.getToken() + "_" + parameter.getRequestID();
        GetArvutidWithParametersResponse response = new GetArvutidWithParametersResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setRequestID(parameter.getRequestID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            List<ArvutiType> matches = dataService.getArvutidWithParameters(parameter);
            if (matches.isEmpty()) {
                statusType.setMessage("No computers were found");
                response.setStatusMessage(statusType);
                return response;
            } else {
                matches.forEach(response.getArvuti()::add);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetArvutidWithParametersResponse) {
                response = (GetArvutidWithParametersResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public AddOpsysToArvutiResponse addOpsysToArvuti(AddOpsysToArvutiRequest parameter) {
        String responseString = checkTokens(parameter.getToken(), parameter.getRequestID());
        String key = parameter.getToken() + "_" + parameter.getRequestID();
        AddOpsysToArvutiResponse response = new AddOpsysToArvutiResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setRequestID(parameter.getRequestID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            List<ArvutiType> addedArvutid = dataService.addOpsysToArvuti(parameter.getOpsysID(), parameter.getArvutiID());
            if (addedArvutid.isEmpty()) {
                statusType.setMessage("No computers with these ids could be found");
                response.setStatusMessage(statusType);
                return response;
            } else {
                addedArvutid.forEach(response.getArvuti()::add);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
           Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof AddOpsysToArvutiResponse) {
                response = (AddOpsysToArvutiResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }

    public GetOpsystemsInArvutiResponse getOpsystemsInArvuti(GetOpsystemsInArvutiRequest parameter) {
        String responseString = checkTokens(parameter.getToken(), parameter.getRequestID());
        String key = parameter.getToken() + "_" + parameter.getRequestID();
        GetOpsystemsInArvutiResponse response = new GetOpsystemsInArvutiResponse();
        StatusType statusType = new StatusType();
        statusType.setMessage(responseString);
        statusType.setRequestID(parameter.getRequestID());
        response.setStatusMessage(statusType);
        if (responseString.equals("OK")) {
            List<OpsysType> opsystemsInArvuti = dataService.getOpsystemsInArvuti(parameter.getArvutiID());
            if (opsystemsInArvuti == null) {
                statusType.setMessage("No computer with this ID exists!");
                response.setStatusMessage(statusType);
            } else {
                opsystemsInArvuti.forEach(response.getOpsys()::add);
            }
            dataService.addResponseForKey(key, response);
        }
        if (responseString.equals("Incorrect request id!")) {
            Object rp = dataService.getResponseForDuplicateKey(key);
            if (rp instanceof GetOpsystemsInArvutiResponse) {
                response = (GetOpsystemsInArvutiResponse) rp;
                statusType.setMessage("Duplicate request id! Returning previous response!");
                response.setStatusMessage(statusType);
            }
        }
        return response;
    }
    
    private String checkTokens(String token, String requestID) {        
        String key = token + "_" + requestID;
        boolean correctToken = token.equals("salajane");
        boolean correctId =  dataService.checkForDuplicateKey(key);
        
        if (correctToken && correctId) {
            dataService.addUsedRequestId(requestID);
            return "OK";
        } else if (correctToken && !correctId) {
            return "Incorrect request id!";
        } else {
            return "Incorrect API token!";  
        }
    }
    
}
