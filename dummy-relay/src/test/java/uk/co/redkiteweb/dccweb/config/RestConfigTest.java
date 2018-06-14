package uk.co.redkiteweb.dccweb.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class RestConfigTest {

    @Test
    public void testRestTemplate() {
        assertNotNull(new RestConfig().getRestTemplate());
    }

}
