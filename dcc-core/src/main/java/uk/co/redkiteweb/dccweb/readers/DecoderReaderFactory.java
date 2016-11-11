package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by shawn on 11/11/16.
 */
@Component
public class DecoderReaderFactory implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public DecoderReader createInstance() {
        return context.getBean(DecoderReader.class);
    }
}
