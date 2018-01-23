package uk.co.redkiteweb.dccweb.readers;

import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;

/**
 * Created by shawn on 16/09/16.
 */
public interface ValueType {
    void setValueNode(final Node valueNode);
    void setCVReader(final CVReader cvReader);
    Integer getValue();
    DecoderSetting getSetting();
}
