package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import uk.co.redkiteweb.dccweb.decoders.model.CVDefinition;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;

import java.util.ArrayList;
import java.util.Collection;

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
        when(reader.readDefinitions()).thenReturn(createCVDefinitions());
    }

    @Test
    public void testGetCVValues() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCVValues());
    }

    @Test
    public void testGetCVDefinitions() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCvDefinitions());
    }

    @Test
    public void testGetCVValue() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCVValue("value"));
    }

    @Test(expected = DefinitionException.class)
    public void testGetMissingCVValue() throws DefinitionException {
        decoderDefinition.setDecoderDefFile(DECODER_DEF_FILE);
        assertNotNull(decoderDefinition.getCVValue("missing"));
    }

    private Collection<CVDefinition> createCVDefinitions() {
        final Collection<CVDefinition> definitions = new ArrayList<>();
        final CVDefinition def1 = createCVDefinition("1");
        def1.getValues().add(createCVValue("1", "value"));
        definitions.add(def1);
        final CVDefinition def2 = createCVDefinition("2");
        def2.getValues().add(createCVValue("2", "id"));
        definitions.add(def2);
        return definitions;
    }

    private CVDefinition createCVDefinition(final String number) {
        final CVDefinition definition = new CVDefinition();
        definition.setNumber(number);
        return definition;
    }

    private CVValue createCVValue(final String number, final String name) {
        final CVValue value = new CVValue();
        value.setType(CVValue.Type.VALUE);
        value.setId(number);
        value.setCvNumber(number);
        value.setName(name);
        return value;
    }
}
