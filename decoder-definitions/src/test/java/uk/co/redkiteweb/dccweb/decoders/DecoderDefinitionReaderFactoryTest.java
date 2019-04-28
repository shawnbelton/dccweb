package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class DecoderDefinitionReaderFactoryTest {

    private DecoderDefinitionReaderFactory readerFactory;
    private ApplicationContext context;

    @Before
    public void setup() {
        context = mock(ApplicationContext.class);
        readerFactory = new DecoderDefinitionReaderFactory();
        readerFactory.setApplicationContext(context);
        readerFactory.setDefinitionsPath("src/test/resources/local");
    }

    @Test
    public void testGetInstanceFromFile() throws DefinitionException {
        when(context.getBean(DecoderDefinitionReader.class)).thenReturn(mock(DecoderDefinitionReader.class));
        assertNotNull(readerFactory.newInstance("123-456.xml"));
    }

    @Test
    public void testGetInstanceFromResource() throws DefinitionException {
        when(context.getBean(DecoderDefinitionReader.class)).thenReturn(mock(DecoderDefinitionReader.class));
        assertNotNull(readerFactory.newInstance("/local/123-456.xml"));
    }

    @Test(expected = DefinitionException.class)
    public void testGetInstanceFail() throws DefinitionException {
        when(context.getBean(DecoderDefinitionReader.class)).thenReturn(mock(DecoderDefinitionReader.class));
        assertNotNull(readerFactory.newInstance("/missing.xml"));
    }

    @Test(expected = DefinitionException.class)
    public void testGetWrongFileType() throws DefinitionException {
        when(context.getBean(DecoderDefinitionReader.class)).thenReturn(mock(DecoderDefinitionReader.class));
        assertNotNull(readerFactory.newInstance("/notXml.xml"));
    }
}
