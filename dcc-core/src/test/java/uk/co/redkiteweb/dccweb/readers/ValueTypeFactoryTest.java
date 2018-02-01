package uk.co.redkiteweb.dccweb.readers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 19/09/16.
 */
@RunWith(JUnit4.class)
public class ValueTypeFactoryTest {

    private ValueTypeFactory valueTypeFactory;
    private ApplicationContext context;

    @Before
    public void setup() {
        context = mock(ApplicationContext.class);
        valueTypeFactory = new ValueTypeFactory();
        valueTypeFactory.setApplicationContext(context);
    }

    @Test
    public void testGetValueInstance() throws DefinitionException {
        when(context.getBean(anyString(), eq(ValueType.class))).thenReturn(mock(ValueType.class));
        final Node node = getValueType("value");
        assertNotNull(valueTypeFactory.getInstance(node, mock(CVReader.class), true));
    }

    @Test(expected = DefinitionException.class)
    public void testGetValueInstanceException() throws DefinitionException {
        when(context.getBean(anyString(), eq(ValueType.class))).thenThrow(mock(NoSuchBeanDefinitionException.class));
        final Node node = getValueType("value");
        valueTypeFactory.getInstance(node, mock(CVReader.class), true);
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
