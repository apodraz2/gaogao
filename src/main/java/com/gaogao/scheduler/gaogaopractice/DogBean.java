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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Dog createDog(String name, String birthday) {
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
        //em.flush();
        return dog;
    }
    
    //returns all dogs
    public List<Dog> getDogList() {
        return em.createQuery("select d from Dog d").getResultList();
    }
    
    //Need to create statement to get all owners assigned to dog
    public List<Owner> getOwnerList(Dog d) {
        //TODO
        
        return d.getOwners();
    }
    
    //Need to create SQL statement to add owner to the dog
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addOwner(Owner o, Dog d) {
        //TODO
        
        d.getOwners().add(o);
        em.merge(d);
        em.flush();
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeOwner(String username, Dog d) {
        
        for(Owner o : d.getOwners()) {
            if(username.equals(o.getEmail())) {
                d.getOwners().remove(o);
            }
        }
        
        em.merge(d);
        em.flush();
    }
    
    //Statement to return all events assigned to that dog
    public List<Event> getEventList(Dog d) {
        //TODO
        return d.getEvents();
    }
    
    //Statement to add event to dog
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addEvent(Event e, Dog d) {
        //TODO
        d.getEvents().add(e);
        em.merge(d);
        em.flush();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeEvent(Event e, Dog d) {
        for (Event event : d.getEvents()) {
            if(e.equals(event)) {
                d.getEvents().remove(event);
            }
        }
        
        em.merge(d);
        em.flush();
    }
}
