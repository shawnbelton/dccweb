package uk.co.redkiteweb.dccweb.webapp.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by shawn on 05/07/17.
 */
@RunWith(JUnit4.class)
public class HomeTest {

    @Test
    public void testHome() {
        final Home home = new Home();
        assertEquals("../static/index", home.getHome());
    }

}
