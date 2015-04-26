/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.gaogaopractice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author adampodraza
 */
@Stateless
public class DogBean {
    
    @PersistenceContext(unitName="gaogaoPracticePU")
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public void createDog(String name, String birthday) {
        Dog dog = new Dog();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d;
        try {
            d = sdf.parse(birthday);
            dog.setBirthday(d);
        } catch (ParseException ex) {
            
        }
        
        dog.setName(name);
        
        
        em.persist(dog);
    }
    
    public List<Dog> getDogList() {
        return em.createQuery("select d from Dog d").getResultList();
    }
}
