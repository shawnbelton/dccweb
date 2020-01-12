package uk.co.redkiteweb.dccweb.decoders.types;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;

import java.util.Locale;

/**
 * Created by shawn on 18/09/16.
 */
@Component
@Scope("prototype")
public class ValueTypeFactory implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    public ValueType getInstance(final CVValue value, final CVHandler cvHandler) throws DefinitionException {
        final String type = value.getType().name();
        try {
            final ValueType valueType = context.getBean(String.format("%sValueType", type.toLowerCase(Locale.UK)), ValueType.class);
            valueType.setCVValue(value);
            valueType.setCVReader(cvHandler);
            return valueType;
        } catch (NoSuchBeanDefinitionException exception) {
            throw new DefinitionException(String.format("Unable to find value type for %s", type), exception);
        }
    }

}
