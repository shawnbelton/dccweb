package uk.co.redkiteweb.dccweb.decoders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.decoders.model.CVDefinition;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;
import uk.co.redkiteweb.dccweb.decoders.types.CVHandler;
import uk.co.redkiteweb.dccweb.decoders.types.ValueType;
import uk.co.redkiteweb.dccweb.decoders.types.ValueTypeFactory;
import uk.co.redkiteweb.dccweb.store.LogStore;

import java.util.*;

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

    private ValueType getValueType(final String valueName) throws DefinitionException {
        final CVValue cvValue = decoderDefinition.getCVValue(valueName);
        return valueTypeFactory.getInstance(cvValue, cvHandler);
    }

    public Integer readValue(final String valueName) throws DefinitionException {
        logStore.log("info", String.format("Reading %s", valueName));
        return getValueType(valueName).getValue();
    }

    public List<DecoderSetting> readAllValues() throws DefinitionException {
        final List<DecoderSetting> decoderSettings = new ArrayList<>();
        for(final CVValue cvValue : decoderDefinition.getCVValues()) {
            final ValueType valueType = valueTypeFactory.getInstance(cvValue, cvHandler);
            decoderSettings.add(valueType.getSetting());
        }
        return decoderSettings;
    }

    public Map<Integer, Integer> buildCVs(final Collection<DecoderSetting> decoderSettings) throws DefinitionException {
        final Map<Integer, Integer> cvMap = new HashMap<>();
        for(final CVDefinition cvDefinition : decoderDefinition.getCvDefinitions()) {
            buildCVFromNode(decoderSettings, cvMap, cvDefinition);
        }
        return cvMap;
    }

    private void buildCVFromNode(final Collection<DecoderSetting> decoderSettings, final Map<Integer, Integer> cvMap, final CVDefinition cvDefinition) throws DefinitionException {
        final String[] cvs = cvDefinition.getNumber().split(",");
        for(String cv : cvs) {
            final Integer cvNumber = Integer.parseInt(cv);
            cvMap.put(cvNumber, getCVValue(decoderSettings, cvDefinition.getValues(), cvNumber));
        }
    }

    private Integer getCVValue(final Collection<DecoderSetting> decoderSettings, final Collection<CVValue> cvValues, final Integer cvNumber) throws DefinitionException {
        Integer cvValue = 0;
        for(final CVValue value : cvValues) {
            final ValueType valueType = valueTypeFactory.getInstance(value, cvHandler);
            cvValue |= valueType.getCVValue(cvNumber, decoderSettings);
        }
        return cvValue;
    }
}
