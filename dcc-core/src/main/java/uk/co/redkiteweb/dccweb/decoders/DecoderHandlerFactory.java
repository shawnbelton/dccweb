package uk.co.redkiteweb.dccweb.decoders;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by shawn on 11/11/16.
 */
@Component
public class DecoderHandlerFactory implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public DecoderHandler createInstance() {
        return context.getBean(DecoderHandler.class);
    }
}
