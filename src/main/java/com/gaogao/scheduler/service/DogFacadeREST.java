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
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

/**
 *
 * @author adampodraza
 */
@Stateless
@Path("/dog")
public class DogFacadeREST extends AbstractFacade<Dog> {
    
    @PersistenceContext(unitName = "gaogaoPracticePU")
    private EntityManager em;
    
    @EJB
    private OwnerBean ownerBean;
    
    @EJB
    private MethodBean methodBean;
    

    public DogFacadeREST() {
        super(Dog.class);
    }
    
    @POST
    @Path("/create")
    public String createNewDog(@FormParam("name") String name, 
                                 @FormParam("birthday") String birthday, 
                                 @FormParam("owner") String email) throws ParseException {
                
        Owner o = methodBean.getOwnerFromEmail(email);
        
        return ownerBean.addNewDog(o, name, birthday).toString();
        
    }
    
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Dog entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void edit(@PathParam("id") Long id, Dog entity) {
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
    public Dog find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    @RolesAllowed("admin")
    public List<Dog> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    @RolesAllowed("admin")
    public List<Dog> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
