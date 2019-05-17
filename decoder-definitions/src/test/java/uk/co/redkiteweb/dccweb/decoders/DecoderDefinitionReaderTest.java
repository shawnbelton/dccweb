package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import uk.co.redkiteweb.dccweb.decoders.model.CVDefinition;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;
import uk.co.redkiteweb.dccweb.decoders.model.CVValueOption;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class DecoderDefinitionReaderTest {

    private DecoderDefinitionReader reader;

    @Before
    public void setup() throws DefinitionException {
        final DecoderDefinitionReaderFactory readerFactory = mock(DecoderDefinitionReaderFactory.class);
        final DecoderDefinitionReader mockReader = mock(DecoderDefinitionReader.class);
        when(readerFactory.newInstance(anyString())).thenReturn(mockReader);
        when(mockReader.readDefinitions()).thenReturn(createIncludeDefinitions());
        reader = new DecoderDefinitionReader();
        reader.setReaderFactory(readerFactory);
        reader.setDefinitionDocument(getDefinitionsDocument());
    }

    @Test
    public void testReadDefinitions() throws DefinitionException {
        final Collection<CVDefinition> definitions = reader.readDefinitions();
        assertNotNull(definitions);
        assertEquals(3, definitions.size());
        final Collection<CVValue> values = new TreeSet<>();
        definitions.forEach(cvDefinition -> values.addAll(cvDefinition.getValues()));
        assertEquals(5, values.size());
        final Collection<CVValueOption> options = new TreeSet<>();
        values.forEach(cvValue -> options.addAll(cvValue.getOptions()));
        assertEquals(2, options.size());
    }

    private Document getDefinitionsDocument() {
        final InputStream definitionStream = DecoderDefinitionReaderTest.class.getResourceAsStream("/local/123-456.xml");
        try {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.parse(definitionStream);
        } catch (final ParserConfigurationException | SAXException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Collection<CVDefinition> createIncludeDefinitions() {
        final Collection<CVDefinition> cvDefinitions = new TreeSet<>();
        cvDefinitions.add(createCVDefinition("2"));
        cvDefinitions.add(createCVDefinition("3"));
        return cvDefinitions;
    }

    private CVDefinition createCVDefinition(final String number) {
        final CVDefinition cvDefinition = new CVDefinition();
        cvDefinition.setNumber(number);
        cvDefinition.getValues().add(createCVValue(number, 0));
        cvDefinition.getValues().add(createCVValue(number, 1));
        return cvDefinition;
    }

    private CVValue createCVValue(final String number, final int bit) {
        final CVValue cvValue = new CVValue();
        cvValue.setId(String.format("cv%sbit%d", number, bit));
        cvValue.setName("Inc Value");
        cvValue.setCvNumber(number);
        cvValue.setType(CVValue.Type.OPTION);
        cvValue.getBit().add(bit);
        cvValue.getOptions().add(createCVOption(1));
        cvValue.getOptions().add(createCVOption(2));
        return cvValue;
    }

    private CVValueOption createCVOption(final int number) {
        final CVValueOption cvValueOption = new CVValueOption();
        cvValueOption.setName(String.format("Option%d", number));
        cvValueOption.setValue(number);
        return cvValueOption;
    }
}
