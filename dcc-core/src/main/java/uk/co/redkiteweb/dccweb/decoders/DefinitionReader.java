package uk.co.redkiteweb.dccweb.decoders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.data.store.LogStore;
import uk.co.redkiteweb.dccweb.decoders.types.CVHandler;
import uk.co.redkiteweb.dccweb.decoders.types.ValueType;
import uk.co.redkiteweb.dccweb.decoders.types.ValueTypeFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private CVHandler cvHandler;

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

    public void setCvHandler(final CVHandler cvHandler) {
        this.cvHandler = cvHandler;
    }

    public void setDecoderFile(final String decoderFile) throws DefinitionException {
        decoderDefinition.setDecoderDefFile(decoderFile);
    }

    public ValueType getValueType(final String valueName) throws DefinitionException {
        final Node valueNode = decoderDefinition.getValueNode(valueName);
        return valueTypeFactory.getInstance(valueNode, cvHandler, true);
    }

    public Integer readValue(final String valueName) throws DefinitionException {
        logStore.log("info", String.format("Reading %s", valueName));
        return getValueType(valueName).getValue();
    }

    public List<DecoderSetting> readAllValues() throws DefinitionException {
        final List<DecoderSetting> decoderSettings = new ArrayList<>();
        final NodeList allValueNodes = decoderDefinition.getValueNodes();
        for(int index = 0; index < allValueNodes.getLength(); index++) {
            final Node valueNode = allValueNodes.item(index);
            final ValueType valueType = valueTypeFactory.getInstance(valueNode, cvHandler, true);
            decoderSettings.add(valueType.getSetting());
        }
        return decoderSettings;
    }

    public Map<Integer, Integer> buildCVs(final List<DecoderSetting> decoderSettings) throws DefinitionException {
        final Map<Integer, Integer> cvMap = new HashMap<>();
        final NodeList allCVNodes = decoderDefinition.getCVNodes();
        for(int index = 0; index < allCVNodes.getLength(); index++) {
            final Element cvNode = (Element) allCVNodes.item(index);
            buildCVFromNode(decoderSettings, cvMap, cvNode);
        }
        return cvMap;
    }

    private void buildCVFromNode(final List<DecoderSetting> decoderSettings, final Map<Integer, Integer> cvMap, final Element cvNode) throws DefinitionException {
        final NodeList valueNodes = cvNode.getElementsByTagName("value");
        final String[] cvs = cvNode.getAttribute("number").split(",");
        for(String cv : cvs) {
            final Integer cvNumber = Integer.parseInt(cv);
            cvMap.put(cvNumber, getCVValue(decoderSettings, valueNodes, cvNumber));
        }
    }

    private Integer getCVValue(final List<DecoderSetting> decoderSettings, final NodeList valueNodes, final Integer cvNumber) throws DefinitionException {
        Integer cvValue = 0;
        for(int valueIndex = 0; valueIndex < valueNodes.getLength(); valueIndex++) {
            final Node valueNode = valueNodes.item(valueIndex);
            final ValueType valueType = valueTypeFactory.getInstance(valueNode, cvHandler, true);
            cvValue |= valueType.getCVValue(cvNumber, decoderSettings);
        }
        return cvValue;
    }
}
