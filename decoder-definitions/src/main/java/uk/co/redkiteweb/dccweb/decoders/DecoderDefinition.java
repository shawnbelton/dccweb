package uk.co.redkiteweb.dccweb.decoders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import uk.co.redkiteweb.dccweb.decoders.model.CVDefinition;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;

import java.util.Collection;
import java.util.Optional;
import java.util.TreeSet;

/**
 * Created by shawn on 16/09/16.
 */
@Component
@Scope("prototype")
public class DecoderDefinition {

    private DecoderDefinitionReaderFactory decoderDefinitionReaderFactory;
    private Collection<CVDefinition> cvDefinitions;

    @Autowired
    public void setDecoderDefinitionReaderFactory(DecoderDefinitionReaderFactory decoderDefinitionReaderFactory) {
        this.decoderDefinitionReaderFactory = decoderDefinitionReaderFactory;
    }

    public void setDecoderDefFile(final String decoderDefFile) throws DefinitionException {
        cvDefinitions = decoderDefinitionReaderFactory.newInstance(decoderDefFile).readDefinitions();
    }

    public CVValue getCVValue(final String valueName) throws DefinitionException {
        final Optional<CVValue> value = getCVValues().stream().filter(cvValue -> cvValue.getName().equals(valueName)).findFirst();
        if (value.isPresent()) {
            return value.get();
        } else {
            throw new DefinitionException(String.format("CV Value %s is not found.", valueName));
        }
    }

    public Collection<CVValue> getCVValues() {
        final Collection<CVValue> cvValues = new TreeSet<>();
        cvDefinitions.forEach(cvDefinition -> cvValues.addAll(cvDefinition.getValues()));
        return cvValues;
    }

    public Collection<CVDefinition> getCvDefinitions() {
        return cvDefinitions;
    }


    public static Integer getOrderValue(final String value) {
        final String[] values = value.split(",");
        return Integer.parseInt(values[0]);
    }

}
