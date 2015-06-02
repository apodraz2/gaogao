/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.gaogao.scheduler.beans.MethodBean;
import com.gaogao.scheduler.beans.OwnerBean;
import com.gaogao.scheduler.persistence.Dog;
import com.gaogao.scheduler.persistence.Kennel;
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
@Path("/kennel")
public class KennelFacadeREST extends AbstractFacade<Kennel> {
    @PersistenceContext(unitName = "gaogaoPracticePU")
    private EntityManager em;
    
    @EJB 
    private OwnerBean ownerBean;
    
    @EJB
    private MethodBean methodBean;

    public KennelFacadeREST() {
        super(Kennel.class);
    }

    @POST
    @Path("/create")
    public String createNew(@FormParam("email") String email,
                            @FormParam("name") String name,
                            @FormParam("number") String number,
                            @FormParam("dog") String dog, 
                            @FormParam("owner") String owner){
        Kennel k = new Kennel();
        k.setEmail(email);
        k.setName(name);
        k.setPhoneNumber(number);
        Dog d = methodBean.getDogFromEmailAndName(owner, dog);
        d.getProviderList().add(k);
        k.setDog(d);
        em.merge(d);
        
        return ownerBean.addKennel(k).toString(); 
    }
    
    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Kennel entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") String id, Kennel entity) {
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
    public Kennel find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Kennel> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Kennel> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
