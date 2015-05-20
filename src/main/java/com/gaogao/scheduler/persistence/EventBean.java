/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
public class EventBean {
    
    @PersistenceContext(unitName="gaogaoPracticePU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Event createEvent(String description, String date, Dog dog) {
        Event event = new Event();
        
        event.setDescription(description);
        //event.setDogId(dog);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date d;
        try {
            d = sdf.parse(date);
            event.setDate(d);
        } catch (ParseException ex) {
            
        }
        
        em.persist(event);
        
        return event;
    }
    
    
    
    public List<Event> getEventList() {
        return em.createQuery("select e from Event e").getResultList();
    }
    
    
}
