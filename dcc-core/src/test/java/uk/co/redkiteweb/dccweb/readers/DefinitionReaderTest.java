package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.decoders.DecoderDefinition;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by shawn on 19/09/16.
 */
@RunWith(JUnit4.class)
public class DefinitionReaderTest {

    private DefinitionReader definitionReader;
    private DecoderDefinition decoderDefinition;

    @Before
    public void setup() throws DefinitionException {
        final LogStore logStore = mock(LogStore.class);
        decoderDefinition = mock(DecoderDefinition.class);
        final ValueTypeFactory valueTypeFactory = mock(ValueTypeFactory.class);
        final Node valueNode = mock(Node.class);
        final ValueType valueType = mock(ValueType.class);
        final CVReader cvReader = mock(CVReader.class);
        definitionReader = new DefinitionReader();
        definitionReader.setLogStore(logStore);
        definitionReader.setDecoderDefinition(decoderDefinition);
        definitionReader.setValueTypeFactory(valueTypeFactory);
        definitionReader.setCvReader(cvReader);
        when(decoderDefinition.getValueNode(anyString())).thenReturn(valueNode);
        when(valueTypeFactory.getInstance(any(Node.class))).thenReturn(valueType);
        when(valueType.getValue(any(CVReader.class), any(Node.class))).thenReturn(1);
    }

    @Test
    public void testReadNamedCV() throws DefinitionException {
        assertEquals(new Integer(1), definitionReader.readValue("NamedCV"));
    }

    @Test
    public void testDecoderDefFile() throws DefinitionException {
        definitionReader.setDecoderFile("/10-10.xml");
        verify(decoderDefinition, times(1)).setDecoderDefFile(anyString());
    }

}
