package uk.co.redkiteweb.dccweb.decoders;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.decoders.types.CVHandler;

/**
 * Created by shawn on 15/09/16.
 */
@Component
public class DefinitionReaderFactory implements ApplicationContextAware {

    private static final int MANUFACTURER = 8;
    private static final int VERSION = 7;
    private ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    public DefinitionReader getInstance(final CVHandler cvHandler) throws DefinitionException {
        final Integer manufacturerId = cvHandler.readCV(MANUFACTURER);
        if (manufacturerId == null) {
            throw new DecoderNotDetectedException("No Decoder Detected");
        }
        final Integer version = cvHandler.readCV(VERSION);
        final DefinitionReader definitionReader = getDefinitionReader(manufacturerId, version);
        definitionReader.setCvHandler(cvHandler);
        return definitionReader;
    }

    private DefinitionReader getDefinitionReader(final Integer manufacturedId, final Integer version) throws DefinitionException {
        final String decoderFile = String.format("%d-%d", manufacturedId, version);
        final DefinitionReader definitionReader =  context.getBean(DefinitionReader.class);
        definitionReader.setDecoderFile(decoderFile);
        return definitionReader;
    }
}
