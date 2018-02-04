package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.w3c.dom.Element;
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
        final Node valueNode = createValueNode();
        optionValueType = new OptionValueType();
        optionValueType.setValueNode(valueNode);
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

    private Node createValueNode() {
        final Node valueNode = mock(Node.class);
        final Node cvNode = mock(Node.class);
        final NamedNodeMap valueNodeAttributes = mock(NamedNodeMap.class);
        final NamedNodeMap cvNodeAttributes = mock(NamedNodeMap.class);
        when(valueNode.getAttributes()).thenReturn(valueNodeAttributes);
        when(valueNode.getParentNode()).thenReturn(cvNode);
        when(cvNode.getAttributes()).thenReturn(cvNodeAttributes);
        final Node numberNode = mock(Node.class);
        final Node bitNode = mock(Node.class);
        final Node nameAttribute = mock(Node.class);
        when(valueNodeAttributes.getNamedItem(eq("name"))).thenReturn(nameAttribute);
        when(cvNodeAttributes.getNamedItem(eq("number"))).thenReturn(numberNode);
        when(valueNodeAttributes.getNamedItem(eq("bit"))).thenReturn(bitNode);
        when(nameAttribute.getTextContent()).thenReturn("name");
        when(numberNode.getTextContent()).thenReturn("1");
        when(bitNode.getTextContent()).thenReturn("0");
        final NodeList optionNodes = createOptionNodes();
        when(valueNode.getChildNodes()).thenReturn(optionNodes);
        return valueNode;
    }

    private NodeList createOptionNodes() {
        final NodeList optionNodes = mock(NodeList.class);
        when(optionNodes.getLength()).thenReturn(2);
        when(optionNodes.item(eq(0))).thenReturn(mock(Node.class));
        final Element optionNode = mock(Element.class);
        when(optionNodes.item(eq(1))).thenReturn(optionNode);
        final NamedNodeMap optionAttributes = mock(NamedNodeMap.class);
        when(optionNode.getAttributes()).thenReturn(optionAttributes);
        final Node valueNode = mock(Node.class);
        final Node optionTextNode = mock(Node.class);
        when(optionAttributes.getNamedItem("value")).thenReturn(valueNode);
        when(optionAttributes.getNamedItem("option")).thenReturn(optionTextNode);
        when(valueNode.getTextContent()).thenReturn("1");
        when(optionTextNode.getTextContent()).thenReturn("option");
        return optionNodes;
    }
}
