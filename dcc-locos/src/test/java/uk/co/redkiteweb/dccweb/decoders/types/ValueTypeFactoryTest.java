package uk.co.redkiteweb.dccweb.decoders.types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;

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
        final CVValue cvValue = getValueType("VALUE");
        assertNotNull(valueTypeFactory.getInstance(cvValue, mock(CVHandler.class)));
    }

    @Test(expected = DefinitionException.class)
    public void testGetValueInstanceException() throws DefinitionException {
        when(context.getBean(anyString(), eq(ValueType.class))).thenThrow(mock(NoSuchBeanDefinitionException.class));
        final CVValue cvValue = getValueType("VALUE");
        valueTypeFactory.getInstance(cvValue, mock(CVHandler.class));
    }

    private CVValue getValueType(final String flag) {
        final CVValue cvValue = mock(CVValue.class);
        when(cvValue.getType()).thenReturn(CVValue.Type.valueOf(flag));
        return cvValue;
    }

}
