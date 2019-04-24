package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 18/09/16.
 */
@RunWith(JUnit4.class)
public class DecoderDefinitionTest {

    private static final String DECODER_DEF_FILE = "/151-255.xml";
    private DecoderDefinition decoderDefinition;
    private DecoderDefinitionReader reader;

    @Before
    public void setup() throws DefinitionException {
        decoderDefinition = new DecoderDefinition();
        reader = mock(DecoderDefinitionReader.class);
        final DecoderDefinitionReaderFactory readerFactory = mock(DecoderDefinitionReaderFactory.class);
        when(readerFactory.newInstance(anyString())).thenReturn(reader);
        decoderDefinition.setDecoderDefinitionReaderFactory(readerFactory);
    }

    @Test
    public void testGetNodes() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCVValues());
    }

    @Test
    public void testGetCVNodes() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCvDefinitions());
    }

}
