package uk.co.redkiteweb.dccweb.readers;

import org.w3c.dom.Node;

/**
 * Created by shawn on 16/09/16.
 */
public class Value implements ValueType {

    private DefinitionReader definitionReader;
    private Node valueNode;

    public Value(final DefinitionReader definitionReader, final Node valueNode) {
        this.definitionReader = definitionReader;
        this.valueNode = valueNode;
    }

    @Override
    public Integer getValue() {
        final String[] cvs = valueNode.getParentNode().getAttributes().getNamedItem("number").getTextContent().split(",");
        int value = 0;
        for(String cv : cvs) {
            value *= 256;
            value += definitionReader.readCV(Integer.parseInt(cv));
        }
        final Node maskNode = valueNode.getAttributes().getNamedItem("mask");
        if (maskNode!=null) {
            value &= Integer.parseInt(maskNode.getTextContent());
        }
        return value;
    }
}
