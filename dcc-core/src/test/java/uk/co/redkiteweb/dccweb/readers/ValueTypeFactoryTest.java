package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 19/09/16.
 */
@RunWith(JUnit4.class)
public class ValueTypeFactoryTest {

    private ValueTypeFactory valueTypeFactory;
    private Value value;
    private Flag flag;

    @Before
    public void setup() {
        value = mock(Value.class);
        flag = mock(Flag.class);
        valueTypeFactory = new ValueTypeFactory();
        valueTypeFactory.setValue(value);
        valueTypeFactory.setFlag(flag);
    }

    @Test
    public void testGetValueInstance() {
        final Node node = getValueType("value");
        assertNotNull(valueTypeFactory.getInstance(node));
    }

    @Test
    public void testGetFlagInstance() {
        final Node node = getValueType("flag");
        assertNotNull(valueTypeFactory.getInstance(node));
    }

    @Test
    public void testGetInvalidInstance() {
        final Node node = getValueType("invalid");
        assertNull(valueTypeFactory.getInstance(node));
    }

    private Node getValueType(String flag) {
        final Node node = mock(Node.class);
        final NamedNodeMap namedNodeMap = mock(NamedNodeMap.class);
        when(node.getAttributes()).thenReturn(namedNodeMap);
        when(namedNodeMap.getNamedItem(anyString())).thenReturn(node);
        when(node.getTextContent()).thenReturn(flag);
        return node;
    }

}
