package uk.co.redkiteweb.dccweb.readers;

import org.w3c.dom.Node;
import uk.co.redkiteweb.dccweb.data.DecoderSetting;

public abstract class AbstractValueType implements ValueType {

    private Node valueNode;
    private CVReader cvReader;
    private boolean useCache;

    @Override
    public void setValueNode(final Node valueNode) {
        this.valueNode = valueNode;
    }

    @Override
    public void setCVReader(final CVReader cvReader) {
        this.cvReader = cvReader;
    }

    @Override
    public void setUseCache(final boolean useCache) {
        this.useCache = useCache;
    }

    protected Node getValueNode() {
        return valueNode;
    }

    protected Integer getCVValue(final Integer cvNumber) {
        return cvReader.readCV(cvNumber, useCache);
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
