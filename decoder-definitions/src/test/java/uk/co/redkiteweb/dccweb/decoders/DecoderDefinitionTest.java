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

    private DecoderDefinition decoderDefinition;

    @Before
    public void setup() {
        decoderDefinition = new DecoderDefinition();
    }

    @Test(expected = DefinitionException.class)
    public void testSetDecoderFile() throws DefinitionException {
        decoderDefinition.setDecoderDefFile("/missing.xml");
    }

    @Test
    public void testGetNode() throws DefinitionException {
        decoderDefinition.setDecoderDefFile("/151-255.xml");
        assertNotNull(decoderDefinition.getValueNode("Short Address"));
    }
}
