package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 11/11/16.
 */
@RunWith(JUnit4.class)
public class DecoderHandlerFactoryTest {

    private DecoderHandlerFactory decoderHandlerFactory;

    @Before
    public void setup() {
        final ApplicationContext context = mock(ApplicationContext.class);
        final DecoderHandler decoderHandler = mock(DecoderHandler.class);
        when(context.getBean(eq(DecoderHandler.class))).thenReturn(decoderHandler);
        decoderHandlerFactory = new DecoderHandlerFactory();
        decoderHandlerFactory.setApplicationContext(context);
    }

    @Test
    public void testCreateInstance() {
        assertNotNull(decoderHandlerFactory.createInstance());
    }

}
