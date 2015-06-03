
import com.gaogao.scheduler.persistence.Dog;
import com.gaogao.scheduler.persistence.Owner;
import java.text.ParseException;
import java.util.Date;
import static org.junit.Assert.assertEquals;
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
public class DogTest {
    @Test
    public void testCreate() throws ParseException {
        Dog d = new Dog();
        
        assertNotNull(d);
        
        d.setName("Denver");
        
        assertEquals(d.getName(), "Denver");
        
        d.setBirthday("25/12/2015");
        
        assertNotNull(d.getBirthday());
    }
}
