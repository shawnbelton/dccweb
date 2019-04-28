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
import java.util.Collections;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class DecoderDefinitionReaderTest {

    private DecoderDefinitionReader reader;

    @Before
    public void setup() {
        reader = new DecoderDefinitionReader();
        reader.setDefinitionDocument(getDefinitionsDocument());
    }

    @Test
    public void testReadDefinitions() throws DefinitionException {
        final Collection<CVDefinition> definitions = reader.readDefinitions();
        assertNotNull(definitions);
        assertEquals(2, definitions.size());
        final Collection<CVValue> values = new TreeSet<>();
        definitions.forEach(cvDefinition -> values.addAll(cvDefinition.getValues()));
        assertEquals(2, values.size());
        final Collection<CVValueOption> options = new TreeSet<>();
        values.forEach(cvValue -> options.addAll(cvValue.getOptions()));
        assertEquals(1, options.size());
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
}
