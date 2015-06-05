/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.service;

import com.gaogao.scheduler.beans.MethodBean;
import com.gaogao.scheduler.beans.OwnerBean;
import com.gaogao.scheduler.persistence.Dog;
import com.gaogao.scheduler.persistence.Event;
import com.gaogao.scheduler.persistence.Owner;
import java.text.ParseException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author adampodraza
 */
@Stateless
@Path("/event")
public class EventFacadeREST extends AbstractFacade<Event> {
    @PersistenceContext(unitName = "gaogaoPracticePU")
    private EntityManager em;
    
    @EJB
    OwnerBean ownerBean;
    
    @EJB
    private MethodBean methodBean;

    public EventFacadeREST() {
        super(Event.class);
    }
    
    @POST
    @Path("/create")
    public String createNewEvent(@FormParam("name") String dog, 
                                 @FormParam("description") String description,
                                 @FormParam("date") String date,
                                 @FormParam("owner") String email) throws ParseException {
        
        System.out.println(dog);
        System.out.println(description);
        System.out.println(email);
        
        Owner o = methodBean.getOwnerFromEmail(email);
        Dog d = methodBean.getDogFromEmailAndName(email, dog);
        
        return ownerBean.addEvent(o, description, date, d).toString();
        
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Event entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void edit(@PathParam("id") Long id, Event entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @RolesAllowed("admin")
    public Event find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    @RolesAllowed("admin")
    public List<Event> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    @RolesAllowed("admin")
    public List<Event> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    @RolesAllowed("admin")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
