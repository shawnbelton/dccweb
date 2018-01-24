package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import java.util.Locale;

/**
 * Created by shawn on 18/09/16.
 */
@Component
@Scope("prototype")
public class ValueTypeFactory implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public ValueType getInstance(final Node valueNode, final CVReader cvReader, final boolean direct) {
        final String type = valueNode.getAttributes().getNamedItem("type").getTextContent();
        final ValueType valueType = context.getBean(String.format("%sValueType", type.toLowerCase(Locale.UK)), ValueType.class);
        valueType.setValueNode(valueNode);
        valueType.setCVReader(cvReader);
        valueType.setUseCache(direct);
        return valueType;
    }

}
