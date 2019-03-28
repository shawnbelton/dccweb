package uk.co.redkiteweb.dccweb.decoders.types;

import uk.co.redkiteweb.dccweb.data.DecoderSetting;
import uk.co.redkiteweb.dccweb.decoders.model.CVValue;

import java.util.Collection;

/**
 * Created by shawn on 16/09/16.
 */
public interface ValueType {
    void setCVValue(final CVValue value);
    void setCVReader(final CVHandler cvHandler);
    Integer getValue();
    DecoderSetting getSetting();
    Integer getCVValue(final Integer cvNumber, final Collection<DecoderSetting> decoderSettings);
}
