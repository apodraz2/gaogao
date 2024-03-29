/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gaogao.scheduler.beans.MethodBean;
import com.gaogao.scheduler.persistence.Dog;
import com.gaogao.scheduler.persistence.Event;
import com.gaogao.scheduler.persistence.Owner;
import com.gaogao.scheduler.beans.OwnerBean;
import com.gaogao.scheduler.persistence.Kennel;
import com.gaogao.scheduler.persistence.ServiceProvider;
import com.gaogao.scheduler.persistence.Vet;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AccessLocalException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adampodraza
 */
public class ServiceTest {
    
    private static EJBContainer ec;
    private static Context ctx;
    
    private static final String email = "apodra86@gmail.com";
    private static final String password = "12345678";
    
    private OwnerBean ownerBean;
    
    private Owner o;
    
    public ServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Properties props = new Properties();
        props.put(EJBContainer.MODULES, new File("target/classes"));

        ec = EJBContainer.createEJBContainer(props);

        // ec = EJBContainer.createEJBContainer();
        ctx = ec.getContext();
    }
    
    @AfterClass
    public static void tearDownClass() throws NamingException {
        if (ec != null) {
            ctx.close();
            ctx = null;
            ec.close();
            ec = null;
        }
    }
    
    @Before
    public void setUp() throws NamingException {
        ownerBean = (OwnerBean)ec.getContext().lookup("java:global/classes/OwnerBean");
        
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void newUserTest() throws Exception {
        o = ownerBean.createOwner(email, password);
        assertNotNull(o);
        assertEquals(email, o.getEmail());
        assertEquals(password, o.getPassword());
    }
    
    @Test
    public void addDog() throws Exception {
       o = ownerBean.createOwner(email + "a", password + "1");
       
       ownerBean.addNewDog(o, "Denver", "25/05/2015");
       
       assertNotNull(o.getDogList().get(0));
       
       assertEquals("Denver", o.getDogList().get(0).getName());
       
    }
    
    @Test
    public void removeDog() throws Exception {
        
       o = ownerBean.createOwner(email + "b", password + "2");
       
       ownerBean.addNewDog(o, "Denver", "25/05/2015");
       
       assertNotNull(o.getDogList().get(0));
       Dog d = o.getDogList().get(0);
       
       ownerBean.removeDog(d, o);
       
       assertTrue(o.getDogList().isEmpty());
        
    }
    
    @Test
    public void addEvent() throws Exception {
        o = ownerBean.createOwner(email + "c", password + "3");
        
        Dog dog = ownerBean.addNewDog(o, "Denver", "25/05/2015");
        
        assertNotNull(o.getDogList().get(0));
        
        
        
        ownerBean.addEvent(o, "Walk him", "25/05/2015", dog);
        Dog d = o.getDogList().get(0);
        
        Event event = d.getEventList().get(0);
        
        assertNotNull(event);
        
        assertEquals(event.getDescription(), "Walk him");
    }
    
    //TODO 
    @Test
    public void removeEvent() throws Exception {
        o = ownerBean.createOwner(email + "d", password + "4");
        
        Dog dog = ownerBean.addNewDog(o, "Denver", "25/05/2015");
        
        assertNotNull(o.getDogList().get(0));
        
        
        
        ownerBean.addEvent(o, "Walk him", "25/05/2015", dog);
        Dog d = o.getDogList().get(0);
        
        Event event = d.getEventList().get(0);
        
        assertNotNull(event);
        
        assertEquals(event.getDescription(), "Walk him");
        
        ownerBean.removeEvent(d, event);
        
        assertTrue(d.getEventList().isEmpty());
        
    }
    
    @Test
    public void removeOwner() {
        o = ownerBean.createOwner(email + "e", password + "5");
        
        
        ownerBean.removeOwner(o);
    }
    
    @Test
    public void addKennel() {
        o = ownerBean.createOwner(email + "f", password + "6");
        
        ServiceProvider k = ownerBean.addKennel("yay", "yay@yaymail.com", "773-555-5555", email+"f");
        
        o.getProviderList().add(k);
        
        assertNotNull(k);
        
        assertTrue(o.getProviderList().size() == 1);
    }
    
    @Test
    public void removeKennel() {
        o = ownerBean.createOwner(email + "g", password + "7");
        
        Kennel k = ownerBean.addKennel("yay", "boo@boomail.com", "773-555-5555", email+"g");
        
        //o.getProviderList().add(k);
        
        assertNotNull(k);
        
        assertTrue(o.getProviderList().size() <= 1);
        
        ownerBean.removeKennel(k, o);
        
        assertTrue(o.getProviderList().isEmpty());
    }
    
    @Test
    public void addVet() {
        o = ownerBean.createOwner(email + "h", password + "8");
        
        Vet v = ownerBean.addVet("yay", "yo@yomail.com", "773-555-5555", email+"h");
        
        assertNotNull(v);
        
        o.getProviderList().add(v);
        assertFalse(o.getProviderList().isEmpty());
    }
    
    @Test
    public void removeVet() {
        
        o = ownerBean.createOwner(email + "i", password + "9");
        
        Vet v = ownerBean.addVet("yay", "gar@garmail.com", "773-555-5555", email+"i");
        
        assertNotNull(v);
        
        o.getProviderList().add(v);
        assertFalse(o.getProviderList().isEmpty());
        
        ownerBean.removeVet(v, o);
        
        assertTrue(o.getProviderList().isEmpty());
        
    }
    
    @Test
    public void getNumUsers() {
        o = ownerBean.createOwner(email + "j", password + "10");
        boolean a = false;
        try {
            ownerBean.getNumUsers();
        } catch (Exception e) {
            a = true;
        }
        
        assertTrue(a);
        
    }
    
    @Test
    public void getNumDogs() {
        o = ownerBean.createOwner(email + "k", password + "11");
        
        boolean a = false;
        
        try {
            ownerBean.getNumDogs();
        } catch (Exception e) {
            a = true;
        }
        
        assertTrue(a);
    }
    
}
