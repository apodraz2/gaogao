/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.beans;

import com.gaogao.scheduler.persistence.Dog;
import com.gaogao.scheduler.persistence.Owner;
import java.util.Iterator;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;

/**
 *
 * @author adampodraza
 */
@Stateless
public class SingletonBean {

    @EJB
    private OwnerBean ownerBean;
   
    
    public Owner getOwnerFromEmail(String email) {
        Owner o = null;
        for (Owner owner : ownerBean.getOwnerList()) {
            if(owner.getEmail().equals(email)) {
                o = owner;
            }
        }
        
        return o;
    }
    
    public Dog getDogFromEmailAndName(String email, String name) {
        Dog d = null;
        
        Owner o = getOwnerFromEmail(email);
        
        for(Dog dog : ownerBean.getDogList(o)) {
            if(dog.getName().equals(name)) {
                d = dog;
                break;
            }
        }
        return d;
    }
}
