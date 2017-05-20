/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.arvuti;

import ee.ttu.idu0075._2015.ws.arvuti.ArvutiType;
import ee.ttu.idu0075._2015.ws.arvuti.GetArvutidWithParametersRequest;
import ee.ttu.idu0075._2015.ws.arvuti.GetOpsystemsWithParametersRequest;
import ee.ttu.idu0075._2015.ws.arvuti.OpsysType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

/**
 *
 * @author dell
 */
public class Data {
    
    public static List<String> requestIds = new ArrayList<>();
    public static BigInteger opsysId = BigInteger.ZERO; 
    public static BigInteger arvutiId = BigInteger.ZERO; 
    public static List<OpsysType> opsystems = new ArrayList<>();
    public static List<ArvutiType> arvutid = new ArrayList<>();
    public static HashMap<String, Object> requests = new HashMap<>();
    
    protected void addUsedRequestId(String usedRequestId) {
        requestIds.add(usedRequestId);
    }

    protected BigInteger getNewOpsysId() {
        opsysId = opsysId.add(BigInteger.ONE);
        return opsysId;
    }

    protected BigInteger getNewArvutiId() {
        arvutiId = arvutiId.add(BigInteger.ONE);
        return arvutiId;
    }
    
    protected boolean checkForDuplicateKey(String key) {
        return !requests.containsKey(key);
    }
    
    protected Object getResponseForDuplicateKey(String key) {
        return requests.get(key);
    }
    
    protected void addResponseForKey(String key, Object response) {
        requests.put(key, response);
    }
    
    protected OpsysType getOpsysById(BigInteger id) {
        Optional<OpsysType> opsys = opsystems.stream().filter(p -> p.getOpsysID().equals(id)).findFirst();
        if (opsys.isPresent()) {
            return opsys.get();
        } else {
            return null;
        }
    }

    public void addOpsys(OpsysType opsys) {
        opsystems.add(opsys);
    }
    
    protected ArvutiType getArvutiById(BigInteger id) {
        Optional<ArvutiType> arvuti = arvutid.stream().filter(p -> p.getArvutiID().equals(id)).findFirst();
        if (arvuti.isPresent()) {
            return arvuti.get();
        } else {
            return null;
        }
    }

    public void addArvuti(ArvutiType arvuti) {
        arvutid.add(arvuti);
    }

    List<OpsysType> getOpsystemsWithParameters(GetOpsystemsWithParametersRequest parameter) {
        List<OpsysType> matches = opsystems;
        if (parameter.getDeveloper() != null) {
            matches = opsystems.stream().filter(p -> p.getDeveloper().equals(parameter.getDeveloper())).collect(Collectors.toList());
        }
        if (parameter.getName() != null) {
            matches = opsystems.stream().filter(p -> p.getName().equals(parameter.getName())).collect(Collectors.toList());
        }
        return matches;
    }

    List<ArvutiType> getArvutidWithParameters(GetArvutidWithParametersRequest parameter) {
        List<ArvutiType> matches = arvutid;
        if (parameter.getUserName() != null) {
            matches = arvutid.stream().filter(p -> p.getUserName().equals(parameter.getUserName())).collect(Collectors.toList());
        }
        if (parameter.getQuantity() != null) {
            matches = arvutid.stream().filter(p -> p.getQuantity().equals(parameter.getQuantity())).collect(Collectors.toList());
        }
        return matches;
    }

    List<ArvutiType> addOpsysToArvuti(List<BigInteger> opsysID, List<BigInteger> arvutiID) {
        List<ArvutiType> allArvutid = new ArrayList<>(arvutid);
        List<OpsysType> allOpsystems = new ArrayList<>(opsystems);
        allArvutid = allArvutid.stream().filter(a -> arvutiID.contains(a.getArvutiID())).collect(Collectors.toList());
        allOpsystems = allOpsystems.stream().filter(p -> opsysID.contains(p.getOpsysID())).collect(Collectors.toList());
       
        for (ArvutiType arvuti : allArvutid) {
            for (OpsysType opsys : allOpsystems) {
                arvuti.getOpsys().add(opsys);
                BigInteger newQuantity = arvuti.getQuantity().add(BigInteger.ONE);
                arvuti.setQuantity(newQuantity);
            }
        }
        return allArvutid;
    }

    List<OpsysType> getOpsystemsInArvuti(BigInteger arvutiID) {
        Optional<ArvutiType> arvuti = arvutid.stream().filter(a -> a.getArvutiID().equals(arvutiID)).findFirst();
        if (arvuti.isPresent()) {
            return arvuti.get().getOpsys();
        } else {
            return null;
        }
        
    }
}

