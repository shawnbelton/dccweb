package uk.co.redkiteweb.dccweb.decoders;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.decoders.types.CVHandler;
import uk.co.redkiteweb.dccweb.decoders.types.ValueType;
import uk.co.redkiteweb.dccweb.decoders.types.ValueTypeFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
        final CVHandler cvHandler = mock(CVHandler.class);
        definitionReader = new DefinitionReader();
        definitionReader.setLogStore(logStore);
        definitionReader.setDecoderDefinition(decoderDefinition);
        definitionReader.setValueTypeFactory(valueTypeFactory);
        definitionReader.setCvHandler(cvHandler);
        when(decoderDefinition.getValueNode(anyString())).thenReturn(valueNode);
        when(valueTypeFactory.getInstance(any(Node.class), any(CVHandler.class))).thenReturn(valueType);
        when(valueType.getValue()).thenReturn(1);
    }

    @Test
    public void testReadNamedCV() throws DefinitionException {
        assertEquals(new Integer(1), definitionReader.readValue("NamedCV"));
    }

    @Test
    public void testReadAllValues() throws DefinitionException {
        final NodeList nodeList = mock(NodeList.class);
        when(nodeList.getLength()).thenReturn(1);
        when(nodeList.item(anyInt())).thenReturn(mock(Node.class));
        when(decoderDefinition.getValueNodes()).thenReturn(nodeList);
        assertNotNull(definitionReader.readAllValues());
    }

    @Test
    public void testDecoderDefFile() throws DefinitionException {
        definitionReader.setDecoderFile("/10-10.xml");
        verify(decoderDefinition, times(1)).setDecoderDefFile(anyString());
    }

}
