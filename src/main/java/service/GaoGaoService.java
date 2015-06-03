/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;



import com.gaogao.scheduler.beans.OwnerBean;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author adampodraza
 */
@WebService
public class GaoGaoService {
    
    @EJB
    private OwnerBean ownerBean;

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addDog")
    public String addDog(@WebParam (name="owner") String owner) {
        //TODO write your implementation code here:
        return null;
    }

    
    
}
