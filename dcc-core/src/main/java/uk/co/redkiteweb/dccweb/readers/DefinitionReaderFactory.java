package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;

/**
 * Created by shawn on 15/09/16.
 */
@Component
public class DefinitionReaderFactory implements ApplicationContextAware {

    private DccInterface dccInterface;
    private ApplicationContext context;

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public DefinitionReader getInstance(final Integer manufacturerId, final Integer revision) throws DefinitionException {
        final String decoderFile = String.format("/%d-%d.xml", manufacturerId, revision);
        final DefinitionReader definitionReader =  context.getBean(DefinitionReader.class);
        definitionReader.setDecoderFile(decoderFile);
        return definitionReader;
    }

}
