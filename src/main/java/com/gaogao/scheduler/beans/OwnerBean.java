/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.beans;


import com.gaogao.scheduler.persistence.Dog;
import com.gaogao.scheduler.persistence.Event;
import com.gaogao.scheduler.persistence.Kennel;
import com.gaogao.scheduler.persistence.Owner;
import com.gaogao.scheduler.persistence.ServiceProvider;
import com.gaogao.scheduler.persistence.Vet;
import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import javax.annotation.security.RolesAllowed;
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
    private MethodBean methodBean;
    
    @EJB
    private NumTotalDogsBean numTotalDogsBean;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Owner createOwner(String email, String password) {
        Owner owner = new Owner(email, password);
        em.persist(owner);
        em.flush();
        return owner;
    }
    
    //Returns all owners
    //@RolesAllowed("admin")
    public List<Owner> getOwnerList() {
        return em.createQuery("select o from Owner o").getResultList();
    }
    
    @RolesAllowed("admin")
    public int getNumUsers() {
        return em.createQuery("select o from Owner o").getResultList().size();
    }
    
    @RolesAllowed("admin")
    public int getNumDogs() {
        return numTotalDogsBean.getNumDogs();
    }
    
    
    //Need to write statement to return all dogs assigned to the owner
    public static List<Dog> getDogList(Owner o) {
        //TODO
        return o.getDogList();
    } 
    
    //Need to write statement to add a dog to the owner's dog list
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Dog addNewDog(Owner o, String name, String birthday) throws ParseException {
        //TODO
        Dog d = new Dog();
        d.setName(name);
        d.setBirthday(birthday);
        em.merge(d);
        o.getDogList().add(d);
        d.getOwnerList().add(o);
        em.merge(o);
        
        em.flush();
        
        numTotalDogsBean.add(1);
        System.out.println(numTotalDogsBean.getNumDogs());
        
        return d;
    }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addExistingDog(Owner o, Dog d) {
        o.getDogList().add(d);
        d.getOwnerList().add(o);
        em.merge(o);
        em.merge(d);
        em.flush();
    }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeDog(Dog d, Owner o) {
        
        if(o.getDogList().contains(d)) {
            
            o.getDogList().remove(d);
            
            d.getOwnerList().remove(o);
            
            d = em.merge(d);
            em.merge(o);
            em.remove(d);
            em.flush();
            
            numTotalDogsBean.add(-1);
        }
    }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Event addEvent(Owner o, String description, String date, Dog dog) throws ParseException {
        
        
        if(dog != null) {
            Event e = new Event();
            e.setDescription(description);
            e.setDate(date);
            e.setDogId(dog);
            
            
            dog.getEventList().add(e);
            em.merge(e);
            em.merge(dog);
            em.flush();
            
            return e;
        } else {
            throw new NoSuchElementException("No dog exists with that name");
        }
        
    }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeEvent(Dog d, Event e){
        if(d.getEventList().contains(e)) {
            d.getEventList().remove(e);
            em.merge(d);
            e = em.merge(e);
            em.remove(e);
            em.flush();
        }
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeOwner(Owner o) {
        o = em.merge(o);
        em.remove(o);
        em.flush();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Kennel addKennel(String email, String name, String number, String owner) {
        Owner o = methodBean.getOwnerFromEmail(owner);
       
        Kennel k = new Kennel();
        k.setEmail(email);
        k.setName(name);
        k.setPhoneNumber(number);
        k.setOwner(o);
        o.getProviderList().add(k);
        em.persist(k);
        em.merge(o);
        //em.flush();
        return k;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeKennel(Kennel k, Owner o) {
        
        Kennel ken = em.merge(k);
        o.getProviderList().remove(ken);
        em.merge(o);
        em.remove(ken);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Vet addVet(String name, String email, String number, String owner) {
        Owner o = methodBean.getOwnerFromEmail(owner);
        
        Vet v = new Vet();
        
        v.setEmail(email);
        v.setName(name);
        v.setPhoneNumber(number);
        v.setOwner(o);
        o.getProviderList().add(v);
        em.persist(v);
        em.merge(o);
        em.flush();
        
        return v;
        
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void removeVet(Vet v, Owner o) {
        Vet vet = em.merge(v);
        o.getProviderList().remove(v);
        em.remove(vet);
        em.merge(o);
        em.flush();
    }
}
