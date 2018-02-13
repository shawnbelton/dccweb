package uk.co.redkiteweb.dccweb.decoders.types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
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
        flag.setUseCache(true);
    }

    @Test
    public void testTrue() {
        when(cvHandler.readCV(anyInt(), anyBoolean())).thenReturn(8);
        assertEquals(new Integer(1), flag.getValue());
    }

    @Test
    public void testFalse() {
        when(cvHandler.readCV(anyInt(), anyBoolean())).thenReturn(0xf7);
        assertEquals(new Integer(0), flag.getValue());
    }

    @Test
    public void testGetSetting() {
        assertNotNull(flag.getSetting());
    }

}
