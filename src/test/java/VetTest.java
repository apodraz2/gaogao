
import com.gaogao.scheduler.persistence.Vet;
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
public class VetTest {
    @Test
    public void createTest() {
        Vet v = new Vet();
        assertNotNull(v);
    }
}
