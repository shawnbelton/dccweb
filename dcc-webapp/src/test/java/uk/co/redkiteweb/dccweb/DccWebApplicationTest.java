package uk.co.redkiteweb.dccweb;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 15/06/16.
 */
@RunWith(JUnit4.class)
public class DccWebApplicationTest {

    private DccWebApplication dccWebApplication;

    @Before
    public void setUp() {
        dccWebApplication = new DccWebApplication();
    }

    @Test
    public void startApplication() {
        // Nothing we can do here.
    }

    @Test
    public void testDataSource() {
        assertNotNull(dccWebApplication.dataSource());
    }

}
