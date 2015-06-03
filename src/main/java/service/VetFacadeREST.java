/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.gaogao.scheduler.beans.OwnerBean;
import com.gaogao.scheduler.persistence.Vet;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
@Path("/vet")
public class VetFacadeREST extends AbstractFacade<Vet> {
    @PersistenceContext(unitName = "gaogaoPracticePU")
    private EntityManager em;
    
    @EJB
    private OwnerBean ownerBean;

    public VetFacadeREST() {
        super(Vet.class);
    }
    
    @POST
    @Path("/create")
    public String createNew(@FormParam("email") String email,
                            @FormParam("name") String name,
                            @FormParam("number") String number,
                            @FormParam("owner") String owner){
        
        Vet v = ownerBean.addVet(email, name, number, owner);
        
        return v.toString();
        
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Vet entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Vet entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Vet find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Vet> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Vet> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
