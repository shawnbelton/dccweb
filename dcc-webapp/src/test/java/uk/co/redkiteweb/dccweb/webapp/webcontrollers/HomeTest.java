package uk.co.redkiteweb.dccweb.webapp.webcontrollers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class HomeTest {

    private Home home;

    @Before
    public void setUp() {
        home = new Home();
    }

    @Test
    public void home() {
        assertEquals("home", home.home());
    }

}
