package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.decoders.types.CVHandler;

import static org.mockito.Mockito.*;

/**
 * Created by shawn on 18/09/16.
 */
@RunWith(JUnit4.class)
public class DefinitionReaderFactoryTest {

    private DefinitionReaderFactory definitionReaderFactory;
    private DefinitionReader definitionReader;

    @Before
    public void setup() {
        final ApplicationContext context = mock(ApplicationContext.class);
        definitionReaderFactory = new DefinitionReaderFactory();
        definitionReaderFactory.setApplicationContext(context);
        definitionReader = mock(DefinitionReader.class);
        when(context.getBean(eq(DefinitionReader.class))).thenReturn(definitionReader);
    }

    @Test
    public void testGetInstance() throws DefinitionException {
        final CVHandler cvHandler = mock(CVHandler.class);
        when(cvHandler.readCV(eq(8))).thenReturn(1);
        definitionReaderFactory.getInstance(cvHandler);
        verify(definitionReader, times(1)).setDecoderFile(anyString());
    }

    @Test(expected = DecoderNotDetectedException.class)
    public void testNoDecoder() throws DefinitionException {
        final CVHandler cvHandler = mock(CVHandler.class);
        when(cvHandler.readCV(eq(8))).thenReturn(null);
        definitionReaderFactory.getInstance(cvHandler);
    }
}
