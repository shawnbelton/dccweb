package uk.co.redkiteweb.dccweb.services.factory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by shawn on 06/12/16.
 */
@RunWith(JUnit4.class)
public class StepFactoryTest {

    private StepFactory stepFactory;
    private ApplicationContext context;
    private IStep nullStep;

    @Before
    public void setup() {
        context = mock(ApplicationContext.class);
        nullStep = mock(IStep.class);
        stepFactory = new StepFactory();
        stepFactory.setApplicationContext(context);
        stepFactory.setNullStep(nullStep);
    }

    @Test
    public void getInstanceTest() {
        when(context.getBean(anyString(), eq(IStep.class))).thenReturn(mock(IStep.class));
        assertNotNull(stepFactory.getInstance(new MacroStep()));
    }

    @Test
    public void getNullInstanceTest() {
        when(context.getBean(anyString(), eq(IStep.class))).thenThrow(new NoSuchBeanDefinitionException("test"));
        assertNotNull(stepFactory.getInstance(new MacroStep()));
    }
}
