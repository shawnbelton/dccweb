package uk.co.redkiteweb.dccweb.readers;

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
public class DecoderReaderFactoryTest {

    private DecoderReaderFactory decoderReaderFactory;
    private ApplicationContext context;

    @Before
    public void setup() {
        context = mock(ApplicationContext.class);
        final DecoderReader decoderReader = mock(DecoderReader.class);
        when(context.getBean(eq(DecoderReader.class))).thenReturn(decoderReader);
        decoderReaderFactory = new DecoderReaderFactory();
        decoderReaderFactory.setApplicationContext(context);
    }

    @Test
    public void testCreateInstance() {
        assertNotNull(decoderReaderFactory.createInstance());
    }

}
