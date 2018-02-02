package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class OptionTypeValueTest {

    private OptionValueType optionValueType;
    private CVReader cvReader;

    @Before
    public void setup() {
        cvReader = mock(CVReader.class);
        when(cvReader.readCV(anyInt())).thenReturn(1);
        final Node node = mock(Node.class);
        final Node nameNode = mock(Node.class);
        final NamedNodeMap namedNodeMap = mock(NamedNodeMap.class);
        final NodeList optionNodes = mock(NodeList.class);
        when(node.getParentNode()).thenReturn(node);
        when(node.getAttributes()).thenReturn(namedNodeMap);
        when(node.getChildNodes()).thenReturn(optionNodes);
        final Node optionNode = mock(Node.class);
        when(optionNodes.getLength()).thenReturn(1);
        when(optionNodes.item(anyInt())).thenReturn(optionNode);
        final NamedNodeMap optionAttributes = mock(NamedNodeMap.class);
        when(optionNode.getAttributes()).thenReturn(optionAttributes);
        final Node valueNode = mock(Node.class);
        when(optionAttributes.getNamedItem(eq("value"))).thenReturn(valueNode);
        when(valueNode.getTextContent()).thenReturn("1");
        when(optionAttributes.getNamedItem(eq("option"))).thenReturn(optionNode);
        when(optionNode.getTextContent()).thenReturn("optionName");
        when(namedNodeMap.getNamedItem(eq("number"))).thenReturn(node);
        when(namedNodeMap.getNamedItem(eq("name"))).thenReturn(nameNode);
        when(nameNode.getTextContent()).thenReturn("Name");
        when(node.getTextContent()).thenReturn("1");
        final Node bitNode = mock(Node.class);
        when(namedNodeMap.getNamedItem(eq("bit"))).thenReturn(bitNode);
        when(bitNode.getTextContent()).thenReturn("3");
        optionValueType = new OptionValueType();
        optionValueType.setValueNode(node);
        optionValueType.setCVReader(cvReader);
        optionValueType.setUseCache(true);
    }

    @Test
    public void testType() {
        assertEquals("option", optionValueType.getType());
    }

    @Test
    public void testDecoderSetting() {
        assertFalse(optionValueType.getSetting().getDecoderSettingOptions().isEmpty());
    }

}
