package uk.co.redkiteweb.dccweb.macros.factory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.model.MacroStep;
import uk.co.redkiteweb.dccweb.macros.IStep;

/**
 * Created by shawn on 05/12/16.
 */
@Component
public class StepFactory implements ApplicationContextAware {

    private ApplicationContext context;
    private IStep nullStep;

    @Autowired
    @Qualifier("null")
    public void setNullStep(final IStep step) {
        this.nullStep = step;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    public IStep getInstance(final MacroStep step) {
        IStep stepImpl;
        try {
            stepImpl = context.getBean(step.getType(), IStep.class);
        } catch (NoSuchBeanDefinitionException exception) {
            stepImpl = nullStep;
        }
        stepImpl.setMacroStep(step);
        return stepImpl;
    }
}
