/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.gaogao.scheduler.gaogaopractice.Dog;
import com.gaogao.scheduler.gaogaopractice.DogBean;
import com.gaogao.scheduler.gaogaopractice.Owner;
import com.gaogao.scheduler.gaogaopractice.OwnerBean;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void setUp() {
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
        
        OwnerBean ownerBean = (OwnerBean)ec.getContext().lookup("java:global/classes/OwnerBean");
        
        Owner o = ownerBean.createOwner(email, password);
        
        assertNotNull(o);
        assertEquals(email, o.getEmail());
        assertEquals(password, o.getPassword());
    }
    
    @Test
    public void addDog() throws Exception {
       OwnerBean ownerBean = (OwnerBean)ec.getContext().lookup("java:global/classes/OwnerBean");
       
       Owner o = ownerBean.createOwner(email + "t", password + "9");
       ownerBean.addNewDog(o, "Denver", "12/05/2015");
       
       assertNotNull(o.getDogList().get(0));
       
       assertEquals("Denver", o.getDogList().get(0).getName());
       
    }
    
    @Test
    public void removeDog() throws Exception {
        
    }
    
    @Test
    public void addEvent() throws Exception {
    
    }
    
    @Test
    public void removeEvent() throws Exception {
        
    }
    
}
