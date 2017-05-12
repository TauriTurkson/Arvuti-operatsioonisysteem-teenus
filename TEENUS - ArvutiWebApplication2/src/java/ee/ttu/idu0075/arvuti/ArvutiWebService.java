/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.arvuti;

import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.AddOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiOpsysListType;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiOpsysType;
import ee.ttu.idu0075._2015.ws.arvuti.ArvutiType;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiListRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiListResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiOpsysListRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutiRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysListRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysListResponse;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsysRequest;
import ee.ttu.idu0075._2015.ws.arvuti.OpsysType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author dell
 */
@WebService(serviceName = "ArvutiService", portName = "ArvutiPort", endpointInterface = "ee.ttu.idu0075._2015.ws.arvuti.ArvutiPortType", targetNamespace = "http://www.ttu.ee/idu0075/2015/ws/arvuti", wsdlLocation = "WEB-INF/wsdl/ArvutiWebService/ArvutiService.wsdl")
//@SchemaValidation
public class ArvutiWebService {
    
    static int nextOpsysId = 1;
    static int nextArvutiId = 1;
    static List<OpsysType> opsysList = new ArrayList<OpsysType>();
    static List<ArvutiType> arvutiList = new ArrayList<ArvutiType>();
        
    public OpsysType getOpsys(GetOpsysRequest parameter) {
        OpsysType ot = null;
            if (parameter.getToken().equalsIgnoreCase("salajane")) {
                for (int i = 0; i < opsysList.size(); i++) {
                    if (opsysList.get(i).getId().equals(parameter.getId())) {
                        ot = opsysList.get(i);
                    }
                }
            }
            return ot;
    }

    public OpsysType addOpsys(AddOpsysRequest parameter) {
        OpsysType ot = new OpsysType();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            ot.setName(parameter.getName());
            ot.setCode(parameter.getCode());
            ot.setId(BigInteger.valueOf(nextOpsysId++));
            opsysList.add(ot);
        }
        return ot;
    }

    public GetOpsysListResponse getOpsysList(GetOpsysListRequest parameter) {
        GetOpsysListResponse response = new GetOpsysListResponse();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            for (OpsysType opsysType : opsysList) {
                response.getOpsys().add(opsysType);
            }
        }
        return response;
    }

    public ArvutiType getArvuti(GetArvutiRequest parameter) {
        ArvutiType at = null;
            if (parameter.getToken().equalsIgnoreCase("salajane")) {
                for (int i = 0; i < arvutiList.size(); i++) {
                    if (arvutiList.get(i).getId().equals(parameter.getId())) {
                        at = arvutiList.get(i);
                    }
                }
            }
            return at;
    }

    public ArvutiType addArvuti(AddArvutiRequest parameter) {
        ArvutiType at = new ArvutiType();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            at.setId(BigInteger.valueOf(nextArvutiId++));
            at.setArvutiNo(parameter.getArvutiNo());
            at.setUserName(parameter.getUserName());
            at.setArvutiOpsysList(new ArvutiOpsysListType());
            arvutiList.add(at);
        }
        return at;
    }

    public GetArvutiListResponse getArvutiList(GetArvutiListRequest parameter) {
        GetArvutiListResponse response = new GetArvutiListResponse();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            for (ArvutiType arvutiType : arvutiList) {
                if ((parameter.getHasRelatedOpsystems().equalsIgnoreCase("ei" ) && (arvutiType.getArvutiOpsysList() == null || arvutiType.getArvutiOpsysList().getArvutiOpsys().isEmpty()))
                        || (parameter.getHasRelatedOpsystems().equalsIgnoreCase("jah" ) && arvutiType.getArvutiOpsysList() != null && !arvutiType.getArvutiOpsysList().getArvutiOpsys().isEmpty())) {
                    response.getArvuti().add(arvutiType);
                }
            }
        }
        return response;
    }

    public ArvutiOpsysListType getArvutiOpsysList(GetArvutiOpsysListRequest parameter) {
        ArvutiOpsysListType arvutiOpsysList = null;
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            for (int i = 0; i < arvutiList.size(); i++) {
                if ((arvutiList.get(i).getId().equals(parameter.getArvutiId())) 
                        && (arvutiList.get(i).getId().equals(parameter.getName())) 
                        && (arvutiList.get(i).getId().equals(parameter.getRequestID()))) {
                    //arvutiOpsysList = arvutiList.get(i).getArvutiOpsysList();
                }
            }    
        }
        return arvutiOpsysList;           
    }

    public ArvutiOpsysType addArvutiOpsys(AddArvutiOpsysRequest parameter) {
        ArvutiOpsysType arvutiOpsys = new ArvutiOpsysType();
        if (parameter.getToken().equalsIgnoreCase("salajane")) {
            GetOpsysRequest opsysRequest = new GetOpsysRequest();
            opsysRequest.setId(parameter.getOpsysId());
            opsysRequest.setToken(parameter.getToken());
            arvutiOpsys.setOpsys(getOpsys(opsysRequest));
            arvutiOpsys.setQuantity(parameter.getQuantity());
            arvutiOpsys.setVersion(parameter.getVersion());
        
            for (int i = 0; i < arvutiList.size(); i++) {
                if (arvutiList.get(i).getId().equals(parameter.getArvutiId())) {
                    //arvutiList.get(i).getArvutiOpsysList().getArvutiOpsys().add(arvutiOpsys);
                    return arvutiOpsys;
                }

            } 
        }
        return null;
    }
    
}