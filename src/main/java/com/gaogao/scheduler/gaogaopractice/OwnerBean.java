/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.gaogaopractice;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adampodraza
 */
@Stateless
public class OwnerBean {
    @PersistenceContext(unitName="gaogaoPracticePU")
    private EntityManager em;

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
    public void addDog(Dog d, Owner o) {
        //TODO
        o.getDogList().add(d);
        em.merge(o);
        em.flush();
    }
}
