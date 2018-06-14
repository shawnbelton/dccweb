package uk.co.redkiteweb.dccweb.webapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class HomeTest {

    @Test
    public void testHome() {
        assertEquals("../static/index", new Home().getHome());
    }

}
