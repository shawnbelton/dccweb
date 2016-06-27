package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 27/06/16.
 */
@RunWith(JUnit4.class)
public class TrainTest {

    private Train train;

    @Before
    public void setUp() {
        train = new Train();
    }

    @Test
    public void testTrain() {
        train.setTrainId(1);
        train.setName("test");
        train.setNumber("12345");
        assertEquals(new Integer(1), train.getTrainId());
        assertEquals("test", train.getName());
        assertEquals("12345", train.getNumber());
    }

}
