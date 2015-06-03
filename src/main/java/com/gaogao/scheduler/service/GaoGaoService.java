/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.service;



import com.gaogao.scheduler.beans.MethodBean;
import com.gaogao.scheduler.beans.OwnerBean;
import com.gaogao.scheduler.persistence.Dog;
import com.gaogao.scheduler.persistence.Owner;
import java.text.ParseException;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author adampodraza
 * 
 * Access wsdl at localhost:8080/gaogao/GaoGaoService?wsdl
 */
@WebService(serviceName="GaoGaoService")
public class GaoGaoService {
    
    @EJB
    private OwnerBean ownerBean;
    
    @EJB 
    MethodBean methodBean;

    /**
     * Web service operation
     */
    @WebMethod(operationName= "createOwner")
    public String createOwner(@WebParam (name="email") String email,
                              @WebParam (name="password") String password) {
        Owner o = ownerBean.createOwner(email, password);
        
        return o.toString();
    }
    
    @WebMethod(operationName="removeOwner")
    public void removeOwner(@WebParam (name="email") String email) {
        Owner o = methodBean.getOwnerFromEmail(email);
        
        ownerBean.removeOwner(o);
    }
    
    
    @WebMethod(operationName = "addDog")
    public String addDog(@WebParam (name="owner") String owner,
                         @WebParam (name="dog") String name,
                         @WebParam (name="birthday") String birthday) throws ParseException {
        //TODO write your implementation code here:
        Owner o = methodBean.getOwnerFromEmail(owner);
        
        Dog d = ownerBean.addNewDog(o, name, birthday);
        
        return d.toString();
    }

    @WebMethod(operationName = "removeDog")
    public void removeDog(@WebParam (name="Owner") String owner,
                            @WebParam (name="dog") String dog) {
        Owner o = methodBean.getOwnerFromEmail(owner);
        Dog d = methodBean.getDogFromEmailAndName(owner, dog);
        
        ownerBean.removeDog(d, o);
    }
    
    
}
