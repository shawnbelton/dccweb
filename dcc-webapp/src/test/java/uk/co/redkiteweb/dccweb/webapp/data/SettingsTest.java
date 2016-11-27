package uk.co.redkiteweb.dccweb.webapp.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 27/11/16.
 */
@RunWith(JUnit4.class)
public class SettingsTest {

    private Settings settings;

    @Before
    public void setup() {
        settings = new Settings();
        settings.setSerialPort("Port");
        settings.setDccSystem("System");
    }

    @Test
    public void testSerialPort() {
        assertEquals("Port", settings.getSerialPort());
    }

    @Test
    public void testDccSystem() {
        assertEquals("System", settings.getDccSystem());
    }
}
