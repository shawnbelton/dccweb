package uk.co.redkiteweb.dccweb.decoders.types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.decoders.CVHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 19/09/16.
 */
@RunWith(JUnit4.class)
public class ValueValueTypeTest {

    private ValueType value;
    private NamedNodeMap namedNodeMap;

    @Before
    public void setup() {
        final CVHandler cvHandler = mock(CVHandler.class);
        when(cvHandler.readCV(anyInt(), anyBoolean())).thenReturn(1);
        final Node node = mock(Node.class);
        final Node nameNode = mock(Node.class);
        final Node idNode = mock(Node.class);
        namedNodeMap = mock(NamedNodeMap.class);
        when(node.getParentNode()).thenReturn(node);
        when(node.getAttributes()).thenReturn(namedNodeMap);
        when(namedNodeMap.getNamedItem(eq("number"))).thenReturn(node);
        when(namedNodeMap.getNamedItem(eq("name"))).thenReturn(nameNode);
        when(namedNodeMap.getNamedItem("id")).thenReturn(idNode);
        when(nameNode.getTextContent()).thenReturn("Name");
        when(node.getTextContent()).thenReturn("1,2");
        when(idNode.getTextContent()).thenReturn("id1");
        value = new ValueValueType();
        value.setValueNode(node);
        value.setCVReader(cvHandler);
        value.setUseCache(false);
    }

    @Test
    public void testGetValue() {
        assertEquals(new Integer(257), value.getValue());
    }

    @Test
    public void testGetWithMask() {
        final Node maskNode = mock(Node.class);
        when(namedNodeMap.getNamedItem(eq("mask"))).thenReturn(maskNode);
        when(maskNode.getTextContent()).thenReturn("255");
        assertEquals(new Integer(1), value.getValue());
    }

    @Test
    public void testGetSetting() {
        assertNotNull(value.getSetting());
    }
}
