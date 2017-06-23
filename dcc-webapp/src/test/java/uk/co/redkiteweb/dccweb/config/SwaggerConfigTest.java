package uk.co.redkiteweb.dccweb.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.servlet.ServletContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * Created by shawn on 23/06/17.
 */
@RunWith(JUnit4.class)
public class SwaggerConfigTest {

    @Test
    public void testSwaggerConfig() {
        final SwaggerConfig swaggerConfig = new SwaggerConfig();
        assertNotNull(swaggerConfig.api(mock(ServletContext.class)));
    }

}
