package uk.co.redkiteweb.dccweb.dccinterface.messages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.Direction.UP;
import static uk.co.redkiteweb.dccweb.dccinterface.messages.ChangeSpeedMessage.SpeedSteps.STEPS_128;

/**
 * Created by shawn on 20/09/16.
 */
@RunWith(JUnit4.class)
public class ChangeSpeedMessageTest {

    private ChangeSpeedMessage changeSpeedMessage;

    @Before
    public void setup() {
        changeSpeedMessage = new ChangeSpeedMessage();
        changeSpeedMessage.setAddress(1234);
        changeSpeedMessage.setDirection(UP);
        changeSpeedMessage.setSpeed(45);
        changeSpeedMessage.setSpeedSteps(STEPS_128);
    }

    @Test
    public void testAddress() {
        assertEquals(new Integer(1234), (Integer)changeSpeedMessage.getAddress());
    }

    @Test
    public void testDirection() {
        assertEquals(UP, changeSpeedMessage.getDirection());
    }

    @Test
    public void testSpeed() {
        assertEquals(new Integer(45), (Integer)changeSpeedMessage.getSpeed());
    }

    @Test
    public void testSpeedSteps() {
        assertEquals(STEPS_128, changeSpeedMessage.getSpeedSteps());
    }
}