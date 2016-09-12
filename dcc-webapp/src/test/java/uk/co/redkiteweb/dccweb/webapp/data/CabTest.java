package uk.co.redkiteweb.dccweb.webapp.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.data.model.Train;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 12/09/16.
 */
@RunWith(JUnit4.class)
public class CabTest {

    private Cab cab;
    private Train train;

    @Before
    public void setup() {
        train = new Train();
        train.setNumber("12345");
        cab = new Cab();
        cab.setSpeed(0);
        cab.setDirection("UP");
        cab.setTrain(train);
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
    public void testTrain() {
        assertEquals("12345", cab.getTrain().getNumber());
    }

}
