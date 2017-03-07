package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 19/09/16.
 */
@RunWith(JUnit4.class)
public class ValueTest {

    private Value value;
    private CVReader cvReader;
    private Node node;
    private NamedNodeMap namedNodeMap;

    @Before
    public void setup() {
        cvReader = mock(CVReader.class);
        when(cvReader.readCV(anyInt())).thenReturn(1);
        node = mock(Node.class);
        namedNodeMap = mock(NamedNodeMap.class);
        when(node.getParentNode()).thenReturn(node);
        when(node.getAttributes()).thenReturn(namedNodeMap);
        when(namedNodeMap.getNamedItem(eq("number"))).thenReturn(node);
        when(node.getTextContent()).thenReturn("1,2");
        value = new Value();
    }

    @Test
    public void testGetValue() {
        assertEquals(new Integer(257), value.getValue(cvReader, node));
    }

    @Test
    public void testGetWithMask() {
        final Node maskNode = mock(Node.class);
        when(namedNodeMap.getNamedItem(eq("mask"))).thenReturn(maskNode);
        when(maskNode.getTextContent()).thenReturn("255");
        assertEquals(new Integer(1), value.getValue(cvReader, node));
    }
}
