package uk.co.redkiteweb.dccweb.decoders.types;

import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;

import java.util.List;

/**
 * Created by shawn on 16/09/16.
 */
public interface ValueType {
    void setValueNode(final Node valueNode);
    void setCVReader(final CVHandler cvHandler);
    Integer getValue();
    DecoderSetting getSetting();
    Integer getCVValue(final Integer cvNumber, final List<DecoderSetting> decoderSettings);
}
