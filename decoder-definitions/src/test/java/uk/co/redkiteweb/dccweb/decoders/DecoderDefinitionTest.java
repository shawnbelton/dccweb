package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

/**
 * Created by shawn on 18/09/16.
 */
@RunWith(JUnit4.class)
public class DecoderDefinitionTest {

    private static final String DECODER_DEF_FILE = "/151-255.xml";
    private static final String SHORT_ADDRESS = "Short Address";
    private static final String TEST_FILE_XML = "123-456.xml";
    private static final String DEFINITIONS_PATH = "./src/test/resources/local";
    private static final String MISSING_VALUE = "Missing Value";
    private static final String MISSING_XML = "/missing.xml";
    private DecoderDefinition decoderDefinition;

    @Before
    public void setup() {
        decoderDefinition = new DecoderDefinition();
    }

    @Test(expected = DefinitionException.class)
    public void testSetDecoderFile() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(MISSING_XML);
    }

    @Test
    public void testGetNode() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCVValue(SHORT_ADDRESS));
    }

    @Test
    public void testGetNodes() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCVValues());
    }

    @Test(expected = DefinitionException.class)
    public void testGetNodeMissing() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        decoderDefinition.getCVValue(MISSING_VALUE);
    }

    @Test
    public void testGetCVNodes() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCvDefinitions());
    }

    @Test
    public void testFromFile() throws DefinitionException {
        decoderDefinition.setDefinitionsPath(DEFINITIONS_PATH);
        decoderDefinition.setDecoderDefFile(TEST_FILE_XML);
        assertNotNull(decoderDefinition.getCVValue(SHORT_ADDRESS));
    }
}
