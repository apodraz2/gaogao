/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.gaogao.scheduler.gaogaopractice.DogBean;
import com.gaogao.scheduler.gaogaopractice.OwnerBean;
import javax.ejb.embeddable.EJBContainer;
import junit.framework.TestCase;

/**
 *
 * @author adampodraza
 */
public class BeanTest extends TestCase{
    private OwnerBean user;
    private DogBean dog;
    
    protected void setUp() throws Exception {
        //EJBContainer ejbContainer = EJBContainer.createEJBContainer();

        //Object object = ejbContainer.getContext().lookup("java:global/simple-stateless/OwnerBean");
    }
    
    public void addUserTest() {
        
    }
    
}
