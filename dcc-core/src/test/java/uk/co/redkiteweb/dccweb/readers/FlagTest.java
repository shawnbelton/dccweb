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
 * Created by shawn on 20/09/16.
 */
@RunWith(JUnit4.class)
public class FlagTest {

    private Flag flag;
    private Node node;
    private NamedNodeMap namedNodeMap;
    private CVReader cvReader;

    @Before
    public void setup() {
        cvReader = mock(CVReader.class);
        when(cvReader.readCV(anyInt())).thenReturn(1);
        node = mock(Node.class);
        namedNodeMap = mock(NamedNodeMap.class);
        when(node.getParentNode()).thenReturn(node);
        when(node.getAttributes()).thenReturn(namedNodeMap);
        when(namedNodeMap.getNamedItem(eq("number"))).thenReturn(node);
        when(node.getTextContent()).thenReturn("1");
        final Node bitNode = mock(Node.class);
        when(namedNodeMap.getNamedItem(eq("bit"))).thenReturn(bitNode);
        when(bitNode.getTextContent()).thenReturn("3");
        flag = new Flag();
    }

    @Test
    public void testTrue() {
        when(cvReader.readCV(anyInt())).thenReturn(8);
        assertEquals(new Integer(1), flag.getValue(cvReader, node));
    }

    @Test
    public void testFalse() {
        when(cvReader.readCV(anyInt())).thenReturn(0xf7);
        assertEquals(new Integer(0), flag.getValue(cvReader, node));
    }
}
