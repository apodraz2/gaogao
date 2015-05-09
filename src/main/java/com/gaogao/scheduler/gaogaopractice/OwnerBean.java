/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.gaogaopractice;

import java.util.List;
import java.util.NoSuchElementException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adampodraza
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class OwnerBean {
    @PersistenceContext(unitName="gaogaoPracticePU")
    private EntityManager em;
    
    @EJB
    private DogBean dogBean;
    
    @EJB
    private EventBean eventBean;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void createOwner(String email, String password) {
        Owner owner = new Owner(email, password);
        em.persist(owner);
        em.flush();
    }
    
    //Returns all owners
    public List<Owner> getOwnerList() {
        return em.createQuery("select o from Owner o").getResultList();
    }
    
    //Need to write statement to return all dogs assigned to the owner
    public List<Dog> getDogList(Owner o) {
        //TODO
        return o.getDogList();
    } 
    
    //Need to write statement to add a dog to the owner's dog list
    public void addNewDog(Owner o, String name, String birthday) {
        //TODO
        Dog d = dogBean.createDog(name, birthday);
        em.merge(d);
        o.getDogList().add(d);
        dogBean.addOwner(o, d);
        em.merge(o);
        
        em.flush();
    }
    
    public void addExistingDog(Owner o, Dog d) {
        o.getDogList().add(d);
        dogBean.addOwner(o, d);
        em.merge(o);
        em.merge(d);
        em.flush();
    }
    
    public void removeDog(Dog d, Owner o) {
        if(o.getDogList().contains(d)) {
            o.getDogList().remove(d);
            d.getOwners().remove(o);
            if(d.getOwners().size() == 0) {
                em.remove(d);
            }
            em.merge(o);
            em.merge(d);
            em.flush();
        }
    }
    
    public void addEvent(Owner o, String description, String date, String name) {
        Dog dog = null;
        for(Dog d : o.getDogList()) {
            if(name.equals(d.getName())) {
                dog = d;
            }
        }
        
        if(dog != null) {
            Event e = eventBean.createEvent(description, date, dog);
            
            dogBean.addEvent(e, dog);
            em.merge(e);
            em.merge(dog);
            em.flush();
        } else {
            throw new NoSuchElementException("No dog exists with that name");
        }
        
    }
}
