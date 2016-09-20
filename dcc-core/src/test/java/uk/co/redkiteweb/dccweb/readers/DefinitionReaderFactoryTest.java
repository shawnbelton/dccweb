package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 18/09/16.
 */
@RunWith(JUnit4.class)
public class DefinitionReaderFactoryTest {

    private DefinitionReaderFactory definitionReaderFactory;
    private ApplicationContext context;

    @Before
    public void setup() {
        context = mock(ApplicationContext.class);
        definitionReaderFactory = new DefinitionReaderFactory();
        definitionReaderFactory.setApplicationContext(context);
    }

    @Test
    public void testGetInstance() throws DefinitionException {
        final DefinitionReader definitionReader = mock(DefinitionReader.class);
        when(context.getBean(eq(DefinitionReader.class))).thenReturn(definitionReader);
        definitionReaderFactory.getInstance(1,1);
        verify(definitionReader, times(1)).setDecoderFile(anyString());
    }

}
