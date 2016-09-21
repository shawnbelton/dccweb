package uk.co.redkiteweb.dccweb.readers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.dccinterface.DccInterface;
import uk.co.redkiteweb.dccweb.dccinterface.messages.MessageResponse;
import uk.co.redkiteweb.dccweb.dccinterface.messages.ReadCVMessage;
import uk.co.redkiteweb.dccweb.decoders.DecoderDefinition;
import uk.co.redkiteweb.dccweb.decoders.DefinitionException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shawn on 15/09/16.
 */
@Component
@Scope("prototype")
public class DefinitionReader {

    private LogStore logStore;
    private DecoderDefinition decoderDefinition;
    private ValueTypeFactory valueTypeFactory;
    private CVReader cvReader;

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Autowired
    public void setDecoderDefinition(final DecoderDefinition decoderDefinition) {
        this.decoderDefinition = decoderDefinition;
    }

    @Autowired
    public void setValueTypeFactory(final ValueTypeFactory valueTypeFactory) {
        this.valueTypeFactory = valueTypeFactory;
    }

    public void setCvReader(final CVReader cvReader) {
        this.cvReader = cvReader;
    }

    public void setDecoderFile(final String decoderFile) throws DefinitionException {
        decoderDefinition.setDecoderDefFile(decoderFile);
    }

    public Integer readValue(final String valueName) throws DefinitionException {
        logStore.log("info", String.format("Reading %s", valueName));
        final Node valueNode = decoderDefinition.getValueNode(valueName);
        final ValueType valueType = valueTypeFactory.getInstance(valueNode);
        return valueType.getValue(cvReader, valueNode);
    }

}
