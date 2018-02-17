package uk.co.redkiteweb.dccweb.decoders.types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 20/09/16.
 */
@RunWith(JUnit4.class)
public class FlagValueTypeTest {

    private ValueType flag;
    private CVHandler cvHandler;

    @Before
    public void setup() {
        cvHandler = mock(CVHandler.class);
        when(cvHandler.readCV(anyInt())).thenReturn(1);
        final Node node = mock(Node.class);
        final Node nameNode = mock(Node.class);
        final Node idNode = mock(Node.class);
        final NamedNodeMap namedNodeMap = mock(NamedNodeMap.class);
        when(node.getParentNode()).thenReturn(node);
        when(node.getAttributes()).thenReturn(namedNodeMap);
        when(namedNodeMap.getNamedItem(eq("number"))).thenReturn(node);
        when(namedNodeMap.getNamedItem(eq("name"))).thenReturn(nameNode);
        when(namedNodeMap.getNamedItem(eq("id"))).thenReturn(idNode);
        when(nameNode.getTextContent()).thenReturn("Name");
        when(node.getTextContent()).thenReturn("1");
        when(idNode.getTextContent()).thenReturn("id1");
        final Node bitNode = mock(Node.class);
        when(namedNodeMap.getNamedItem(eq("bit"))).thenReturn(bitNode);
        when(bitNode.getTextContent()).thenReturn("3");
        flag = new FlagValueType();
        flag.setValueNode(node);
        flag.setCVReader(cvHandler);
    }

    @Test
    public void testTrue() {
        when(cvHandler.readCV(anyInt())).thenReturn(8);
        assertEquals(new Integer(1), flag.getValue());
    }

    @Test
    public void testFalse() {
        when(cvHandler.readCV(anyInt())).thenReturn(0xf7);
        assertEquals(new Integer(0), flag.getValue());
    }

    @Test
    public void testGetSetting() {
        assertNotNull(flag.getSetting());
    }

    @Test
    public void testCVValueSet() {
        final List<DecoderSetting> decoderSettings = new ArrayList<>();
        decoderSettings.add(createDecoderSetting("Flag", 1));
        decoderSettings.add(createDecoderSetting("Name", 1));
        assertEquals(new Integer(8), flag.getCVValue(1, decoderSettings));
    }

    @Test
    public void testCVValueNotSet() {
        final List<DecoderSetting> decoderSettings = new ArrayList<>();
        decoderSettings.add(createDecoderSetting("Flag", 1));
        decoderSettings.add(createDecoderSetting("Name", 0));
        assertEquals(new Integer(0), flag.getCVValue(1, decoderSettings));
    }

    private DecoderSetting createDecoderSetting(final String name, final Integer value) {
        final DecoderSetting decoderSetting = new DecoderSetting();
        decoderSetting.setName(name);
        decoderSetting.setNewValue(value);
        return decoderSetting;
    }

}
