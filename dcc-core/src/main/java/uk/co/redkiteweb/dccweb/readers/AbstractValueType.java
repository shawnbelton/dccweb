package uk.co.redkiteweb.dccweb.readers;

import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;

public abstract class AbstractValueType implements ValueType {

    private Node valueNode;
    private CVReader cvReader;

    @Override
    public void setValueNode(Node valueNode) {
        this.valueNode = valueNode;
    }

    @Override
    public void setCVReader(CVReader cvReader) {
        this.cvReader = cvReader;
    }

    protected Node getValueNode() {
        return valueNode;
    }

    protected CVReader getCvReader() {
        return cvReader;
    }

    @Override
    public abstract Integer getValue();

    protected abstract String getType();

    @Override
    public DecoderSetting getSetting() {
        final DecoderSetting setting = new DecoderSetting();
        setting.setType(getType());
        setting.setName(getName());
        setting.setValue(getValue());
        return setting;
    }

    private String getName() {
        return valueNode.getAttributes().getNamedItem("name").getTextContent();
    }
}
