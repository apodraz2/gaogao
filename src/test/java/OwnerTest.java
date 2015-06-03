
import com.gaogao.scheduler.persistence.Dog;
import com.gaogao.scheduler.persistence.Owner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adampodraza
 */
public class OwnerTest {
    
    @Test
    public void testCreate() {
        Owner o = new Owner();
        
        assertNotNull(o);
        
        o.setEmail("apodra86@gmail.com");
        
        assertEquals(o.getEmail(), "apodra86@gmail.com");
        
        o.setPassword("1234567");
        
        assertEquals(o.getPassword(), "1234567");
    }
    
    
}
