package uk.co.redkiteweb.dccweb.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Loco;

import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 12/09/16.
 */
@RunWith(JUnit4.class)
public class CabTest {

    private Cab cab;

    @Before
    public void setup() {
        final Loco loco = new Loco();
        loco.setNumber("12345");
        cab = new Cab();
        cab.setSpeed(0);
        cab.setDirection("UP");
        cab.setSteps("128");
        cab.setLoco(loco);
        cab.setCabFunctions(new TreeSet<CabFunction>());
    }

    @Test
    public void testSpeed() {
        assertEquals(0, cab.getSpeed());
    }

    @Test
    public void testDirection() {
        assertEquals("UP", cab.getDirection());
    }

    @Test
    public void testLoco() {
        assertEquals("12345", cab.getLoco().getNumber());
    }

    @Test
    public void testSteps() {
        assertEquals("128", cab.getSteps());
    }

    @Test
    public void testCabFunctions() {
        assertEquals(0, cab.getCabFunctions().size());
    }
}
