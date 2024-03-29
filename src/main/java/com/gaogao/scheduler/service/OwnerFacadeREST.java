/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gaogao.scheduler.service;

import com.gaogao.scheduler.persistence.Owner;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("/owner")
public class OwnerFacadeREST extends AbstractFacade<Owner> {
    @PersistenceContext(unitName = "gaogaoPracticePU")
    private EntityManager em;

    public OwnerFacadeREST() {
        super(Owner.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void create(Owner entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void edit(@PathParam("id") String id, Owner entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    @RolesAllowed("admin")
    public Owner find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    @RolesAllowed("admin")
    public List<Owner> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    @RolesAllowed("admin")
    public List<Owner> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
