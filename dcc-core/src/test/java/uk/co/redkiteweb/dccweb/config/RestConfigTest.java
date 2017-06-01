package uk.co.redkiteweb.dccweb.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 01/06/17.
 */
@RunWith(JUnit4.class)
public class RestConfigTest {

    @Test
    public void testRestTemplate() {
        final RestConfig restConfig = new RestConfig();
        assertNotNull(restConfig.getRestTemplate());
    }

}
