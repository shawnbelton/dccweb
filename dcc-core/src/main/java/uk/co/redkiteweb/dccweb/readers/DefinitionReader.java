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

    private DccInterface dccInterface;
    private LogStore logStore;
    private DecoderDefinition decoderDefinition;
    private final Map<Integer, Integer> cachedCVs;

    public DefinitionReader() {
        cachedCVs = new HashMap<Integer, Integer>();
    }

    @Autowired
    public void setLogStore(final LogStore logStore) {
        this.logStore = logStore;
    }

    @Autowired
    public void setDccInterface(final DccInterface dccInterface) {
        this.dccInterface = dccInterface;
    }

    public void setDecoderFile(final String decoderFile) throws DefinitionException {
        decoderDefinition = new DecoderDefinition(decoderFile);
    }

    public Integer readValue(final String valueName) throws DefinitionException {
        logStore.log("info", String.format("Reading %s", valueName));
        final ValueType valueType = getValueType(decoderDefinition.getValueNode(valueName));
        return valueType.getValue();
    }

    public Map<Integer, Integer> getCVCache() {
        return cachedCVs;
    }

    private ValueType getValueType(final Node valueNode) {
        ValueType valueType = null;
        final String type = valueNode.getAttributes().getNamedItem("type").getTextContent();
        if ("value".equalsIgnoreCase(type)) {
            valueType = new Value(this, valueNode);
        } else if ("flag".equalsIgnoreCase(type)) {
            valueType = new Flag(this, valueNode);
        }
        return valueType;
    }

    public Integer readCV(final int cvNumber) {
        Integer cvValue;
        if (cachedCVs.containsKey(cvNumber)) {
            cvValue = cachedCVs.get(cvNumber);
        } else {
            final ReadCVMessage readCVMessage = new ReadCVMessage();
            readCVMessage.setCvReg(cvNumber);
            cvValue = getCvValue(dccInterface.sendMessage(readCVMessage));
            cachedCVs.put(cvNumber, cvValue);
        }
        return cvValue;
    }

    private static Integer getCvValue(final MessageResponse response) {
        Integer cvValue = null;
        if (MessageResponse.MessageStatus.OK.equals(response.getStatus())) {
            cvValue = (Integer)response.get("CVData");
        }
        return cvValue;
    }

}
