package uk.co.redkiteweb.dccweb.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 30/06/16.
 */
@RunWith(JUnit4.class)
public class DccManufacturerTest {

    private DccManufacturer dccManufacturer;

    @Before
    public void setUp() {
        dccManufacturer = new DccManufacturer();
        dccManufacturer.setManufacturerId(1);
        dccManufacturer.setManufacturer("Test");
        dccManufacturer.setCountry("UK");
    }

    @Test
    public void testEntity() {
        assertEquals(new Integer(1), dccManufacturer.getManufacturerId());
        assertEquals("Test", dccManufacturer.getManufacturer());
        assertEquals("UK", dccManufacturer.getCountry());
    }
}
